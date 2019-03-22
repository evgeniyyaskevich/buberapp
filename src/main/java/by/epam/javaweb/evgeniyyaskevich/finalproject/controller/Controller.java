package by.epam.javaweb.evgeniyyaskevich.finalproject.controller;

import by.epam.javaweb.evgeniyyaskevich.finalproject.command.ActionCommand;
import by.epam.javaweb.evgeniyyaskevich.finalproject.command.ActionCommandFactory;
import by.epam.javaweb.evgeniyyaskevich.finalproject.command.GetApplicationsCommand;
import by.epam.javaweb.evgeniyyaskevich.finalproject.command.admin.GetParametersForAdminPanelCommand;
import by.epam.javaweb.evgeniyyaskevich.finalproject.command.client.GetDestinationsCommand;
import by.epam.javaweb.evgeniyyaskevich.finalproject.util.ResourceManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login", "/register", "/main", "/order", "/successOrder"})
public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] page = req.getRequestURI().split("/");
        String currentPage = page[page.length - 1];
        processRequest(req, currentPage);

        ResourceManager messageManager = new ResourceManager("config");
        String path = messageManager.getProperty("path.page." + currentPage);
        getServletContext().getRequestDispatcher(path).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processPostRequest(req, resp);
    }

    private void processPostRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ActionCommandFactory commandFactory = ActionCommandFactory.getInstance();
        ActionCommand currentCommand = commandFactory.defineCommand(request);
        if (currentCommand != null) {
            String page = currentCommand.execute(request);
            Object errorMessage = request.getAttribute("errorMessage");
            if (errorMessage != null) {
                doGet(request, response);
            } else {
                response.sendRedirect(getLocationFromPath(page));
            }
        }
    }

    private String getLocationFromPath(String path) {
        String[] s = path.split("/");
        String fileName = s[s.length - 1];
        s = fileName.split("\\.");
        return s[s.length - 2];
    }

    private void processRequest(HttpServletRequest request, String currentPage) {
        switch (currentPage) {
            case "main":
                new GetDestinationsCommand().execute(request);
                new GetApplicationsCommand().execute(request);
                new GetParametersForAdminPanelCommand().execute(request);
                break;
        }
    }
}
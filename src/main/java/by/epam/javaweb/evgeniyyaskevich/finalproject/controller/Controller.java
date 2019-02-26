package by.epam.javaweb.evgeniyyaskevich.finalproject.controller;

import by.epam.javaweb.evgeniyyaskevich.finalproject.command.ActionCommand;
import by.epam.javaweb.evgeniyyaskevich.finalproject.command.ActionCommandFactory;
import by.epam.javaweb.evgeniyyaskevich.finalproject.util.ResourceManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login", "/register", "/main"})
public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] page = req.getRequestURI().split("/");
        String currentPage = page[page.length - 1];
        ResourceManager messageManager = new ResourceManager("config");
        String path = messageManager.getProperty("path.page." + currentPage);
        getServletContext().getRequestDispatcher(path).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*if (req.getParameter("command") != null) {
            if (req.getParameter("command").equals("login")) {
                LogInCommand signInCommand = new LogInCommand();
                String page = signInCommand.execute(req);
                getServletContext().getRequestDispatcher(page).forward(req, resp);
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(req, resp);*/
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActionCommandFactory commandFactory = ActionCommandFactory.getInstance();
        String commandName = request.getParameter("command");
        ActionCommand currentCommand = commandFactory.defineCommand(request);
        String page = currentCommand.execute(request);
        //TODO: Post Redirect Get
        getServletContext().getRequestDispatcher(page).forward(request, response);
    }

}
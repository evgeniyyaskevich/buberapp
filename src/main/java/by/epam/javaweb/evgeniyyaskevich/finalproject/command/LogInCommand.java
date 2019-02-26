package by.epam.javaweb.evgeniyyaskevich.finalproject.command;

import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.User;
import by.epam.javaweb.evgeniyyaskevich.finalproject.service.UserService;
import by.epam.javaweb.evgeniyyaskevich.finalproject.util.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogInCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private UserService userService = new UserService();

    //TODO: move logic to service
    //TODO: you need to replace any part of your program to minimize strength)
    @Override
    public String execute(HttpServletRequest request) {
        String page;

        String login = request.getParameter(PARAM_NAME_LOGIN);
        char[] password = request.getParameter(PARAM_NAME_PASSWORD).toCharArray();
        ResourceManager configManager = new ResourceManager("config");
        ResourceManager messageManager = new ResourceManager("messages");

        if (userService.isUserExist(login)) {
            if (userService.checkPassword(login, password)) {
                User user = userService.getUser(login);
                HttpSession session = request.getSession();
                session.setAttribute("isAuthorized", true);
                session.setAttribute("user", user);
                page = configManager.getProperty("path.page.main");
            } else {
                request.setAttribute("errorMessage", messageManager.getProperty("message.password_error"));
                page = configManager.getProperty("path.page.login");
            }
        } else {
            request.setAttribute("errorMessage", messageManager.getProperty("message.login_error"));
            page = configManager.getProperty("path.page.login");
        }
        return page;
    }
}
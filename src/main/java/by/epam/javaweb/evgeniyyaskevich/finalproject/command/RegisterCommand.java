package by.epam.javaweb.evgeniyyaskevich.finalproject.command;

import by.epam.javaweb.evgeniyyaskevich.finalproject.service.UserService;
import by.epam.javaweb.evgeniyyaskevich.finalproject.util.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class RegisterCommand implements ActionCommand {
    private UserService userService = new UserService();
    //TODO: DAO should be singleton

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String login = request.getParameter("login");
        char[] password = request.getParameter("password").toCharArray();
        char[] confirmPassword = request.getParameter("confirmPassword").toCharArray();

        ResourceManager configManager = new ResourceManager("config");
        ResourceManager messageManager = new ResourceManager("messages");

        if (userService.isUserExist(login)) {
            request.setAttribute("errorMessage", messageManager.getProperty("message.taken_login_error"));
            page = configManager.getProperty("path.page.register");
        } else if (Arrays.equals(password, confirmPassword)) {
            userService.register(login, password);
            userService.fillSession(request.getSession(), login);
            page = configManager.getProperty("path.page.main");
        } else {
            request.setAttribute("errorMessage",
                    messageManager.getProperty("message.confirm_password_error"));
            page = configManager.getProperty("path.page.register");
        }
        return page;
    }
}
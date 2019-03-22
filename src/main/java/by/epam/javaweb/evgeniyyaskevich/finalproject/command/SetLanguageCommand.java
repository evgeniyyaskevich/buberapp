package by.epam.javaweb.evgeniyyaskevich.finalproject.command;

import by.epam.javaweb.evgeniyyaskevich.finalproject.util.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SetLanguageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String language = request.getParameter("language");
        session.setAttribute("language", language);
        String[] uriParts = request.getRequestURI().split("/");
        String key = uriParts[uriParts.length - 1];
        return new ResourceManager("config").getProperty("path.page." + key);
    }
}

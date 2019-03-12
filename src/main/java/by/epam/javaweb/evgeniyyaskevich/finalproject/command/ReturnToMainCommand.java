package by.epam.javaweb.evgeniyyaskevich.finalproject.command;

import by.epam.javaweb.evgeniyyaskevich.finalproject.util.ResourceManager;

import javax.servlet.http.HttpServletRequest;

public class ReturnToMainCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return new ResourceManager("config").getProperty("path.page.main");
    }
}

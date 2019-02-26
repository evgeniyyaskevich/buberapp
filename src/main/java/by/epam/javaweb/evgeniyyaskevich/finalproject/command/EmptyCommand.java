package by.epam.javaweb.evgeniyyaskevich.finalproject.command;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return "";
    }
}
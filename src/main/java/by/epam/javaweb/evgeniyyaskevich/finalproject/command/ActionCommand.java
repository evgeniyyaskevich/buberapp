package by.epam.javaweb.evgeniyyaskevich.finalproject.command;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {
    String execute(HttpServletRequest request);
}
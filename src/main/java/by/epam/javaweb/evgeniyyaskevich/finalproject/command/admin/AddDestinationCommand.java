package by.epam.javaweb.evgeniyyaskevich.finalproject.command.admin;

import by.epam.javaweb.evgeniyyaskevich.finalproject.command.ActionCommand;
import by.epam.javaweb.evgeniyyaskevich.finalproject.service.DestinationService;
import by.epam.javaweb.evgeniyyaskevich.finalproject.util.ResourceManager;

import javax.servlet.http.HttpServletRequest;

public class AddDestinationCommand implements ActionCommand {
    private DestinationService destinationService = DestinationService.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("destination_name");
        int southCoord = Integer.parseInt(request.getParameter("south_coord"));
        int northCoord = Integer.parseInt(request.getParameter("north_coord"));
        destinationService.insertDestination(name, southCoord, northCoord);
        return new ResourceManager("config").getProperty("path.page.main");
    }
}
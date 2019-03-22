package by.epam.javaweb.evgeniyyaskevich.finalproject.command.admin;

import by.epam.javaweb.evgeniyyaskevich.finalproject.command.ActionCommand;
import by.epam.javaweb.evgeniyyaskevich.finalproject.service.DestinationService;
import by.epam.javaweb.evgeniyyaskevich.finalproject.util.ResourceManager;

import javax.servlet.http.HttpServletRequest;

public class UpdateDestinationCommand implements ActionCommand {
    private DestinationService destinationService = DestinationService.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("destination_name");
        long id = Long.parseLong(request.getParameter("destination_id"));
        int southCoord = Integer.parseInt(request.getParameter("south_coord"));
        int northCoord = Integer.parseInt(request.getParameter("north_coord"));
        destinationService.updateDestination(id, name, southCoord, northCoord);
        return new ResourceManager("config").getProperty("path.page.main");
    }
}

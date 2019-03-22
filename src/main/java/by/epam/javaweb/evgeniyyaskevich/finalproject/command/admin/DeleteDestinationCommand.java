package by.epam.javaweb.evgeniyyaskevich.finalproject.command.admin;

import by.epam.javaweb.evgeniyyaskevich.finalproject.command.ActionCommand;
import by.epam.javaweb.evgeniyyaskevich.finalproject.service.DestinationService;
import by.epam.javaweb.evgeniyyaskevich.finalproject.util.ResourceManager;

import javax.servlet.http.HttpServletRequest;

public class DeleteDestinationCommand implements ActionCommand {
    private DestinationService destinationService = DestinationService.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        long id = Integer.parseInt(request.getParameter("destination_id"));
        destinationService.deleteDestination(id);
        return new ResourceManager("config").getProperty("path.page.main");
    }
}

package by.epam.javaweb.evgeniyyaskevich.finalproject.command.client;

import by.epam.javaweb.evgeniyyaskevich.finalproject.command.ActionCommand;
import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.exception.PersistException;
import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.implementation.MySqlDestinationDao;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.Destination;
import by.epam.javaweb.evgeniyyaskevich.finalproject.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetDestinationsCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(GetDestinationsCommand.class);
    private MySqlDestinationDao destinationDao = MySqlDestinationDao.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        try {
            List<Destination> destinationList = destinationDao.getAll();
            request.setAttribute("destinations", destinationList);
        } catch (PersistException e) {
            LOGGER.error("DestinationDao problems: ", e);
        }
        return new ResourceManager("config").getProperty("path.page.main");
    }
}

package by.epam.javaweb.evgeniyyaskevich.finalproject.command;

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
    private MySqlDestinationDao destinationDao = new MySqlDestinationDao();

    @Override
    public String execute(HttpServletRequest request) {
        List<Destination> destinationList = null;
        try {
            destinationList = destinationDao.getAll();
        } catch (PersistException e) {
            LOGGER.error(e);
        }
        request.setAttribute("destinations", destinationList);
        ResourceManager configManager = new ResourceManager("config");
        return configManager.getProperty("path.page.main");
    }
}

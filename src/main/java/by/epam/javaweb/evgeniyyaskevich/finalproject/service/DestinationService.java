package by.epam.javaweb.evgeniyyaskevich.finalproject.service;

import by.epam.javaweb.evgeniyyaskevich.finalproject.builder.DestinationBuilder;
import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.exception.PersistException;
import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.implementation.MySqlDestinationDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DestinationService {
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);
    private MySqlDestinationDao destinationDao = MySqlDestinationDao.getInstance();

    private static final class SingletonHolder {
        private static final DestinationService INSTANCE = new DestinationService();
    }

    private DestinationService() {}

    public static DestinationService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void insertDestination(String name, int southCoord, int northCoord) {
        DestinationBuilder destinationBuilder = new DestinationBuilder();
        destinationBuilder.setName(name)
                .setSouthCoord(southCoord)
                .setNorthCoord(northCoord);
        try {
            destinationDao.insert(destinationBuilder.build());
        } catch (PersistException e) {
            LOGGER.error("Destination dao problem: ", e);
        }
    }

    public void deleteDestination(long id) {
        DestinationBuilder destinationBuilder = new DestinationBuilder();
        destinationBuilder.setId(id);
        try {
            destinationDao.delete(destinationBuilder.build());
        } catch (PersistException e) {
            LOGGER.error("Destination dao problem: ", e);
        }
    }

    public void updateDestination(long id, String name, int southCoord, int northCoord) {
        DestinationBuilder destinationBuilder = new DestinationBuilder();
        destinationBuilder.setName(name)
                .setId(id)
                .setSouthCoord(southCoord)
                .setNorthCoord(northCoord);
        try {
            destinationDao.update(destinationBuilder.build());
        } catch (PersistException e) {
            LOGGER.error("Destination dao problem: ", e);
        }
    }
}

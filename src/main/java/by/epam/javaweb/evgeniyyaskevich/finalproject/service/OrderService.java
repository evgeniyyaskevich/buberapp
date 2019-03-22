package by.epam.javaweb.evgeniyyaskevich.finalproject.service;

import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.exception.PersistException;
import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.implementation.MySqlApplicationDao;
import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.implementation.MySqlDestinationDao;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.Application;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.ApplicationState;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.CarType;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.Destination;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class OrderService {
    private static final Logger LOGGER = LogManager.getLogger(OrderService.class);
    private static final double MINIVAN_COEF = 1.5;
    private static final double UNIVERSAL_COEF = 1;
    private static final double ELITE_COEF = 2;
    private static final double STANDARD_TARIFF = 1;
    private MySqlDestinationDao destinationDao = MySqlDestinationDao.getInstance();
    private MySqlApplicationDao applicationDao = MySqlApplicationDao.getInstance();
    //TODO: insert constants in database!

    private static final class SingletonHolder {
        private static final OrderService INSTANCE = new OrderService();
    }

    private OrderService() {}

    public static OrderService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void cancelOrder(long id) {
        try {
            Application application = applicationDao.getById(id);
            application.setState(ApplicationState.CANCELED);
            applicationDao.update(application);
        } catch(PersistException e) {
            LOGGER.error("Application Dao problem: ", e);
        }
    }

    public int calculatePrice(String destination, CarType carType) {
        double coefficient = getCoefficient(carType);
        return (int) (calculateDistance(destination) * STANDARD_TARIFF * coefficient) / 2;
    }

    private int calculateDistance(String destinationName) {
        Random random = new Random();
        int currentSouth = random.nextInt() % 50;
        int currentNorth = random.nextInt() % 50;
        Destination destination = new Destination();
        try {
            destination = destinationDao.getByName(destinationName);
        } catch (PersistException e) {
            LOGGER.error("DestinationDao problem: ", e);
        }
        int first = Math.abs(currentNorth - destination.getNorthCoord());
        int second = Math.abs(currentSouth - destination.getSouthCoord());
        return (int) Math.sqrt(first * first + second * second);
    }

    private double getCoefficient(CarType carType) {
        switch (carType) {
            case MINIVAN: {
                return MINIVAN_COEF;
            }
            case UNIVERSAL: {
                return UNIVERSAL_COEF;
            }
            case ELITE: {
                return ELITE_COEF;
            }
            default: {
                LOGGER.warn("Unknown case in switch.");
                return 0;
            }
        }
    }
}

package by.epam.javaweb.evgeniyyaskevich.finalproject.command.client;

import by.epam.javaweb.evgeniyyaskevich.finalproject.builder.ApplicationBuilder;
import by.epam.javaweb.evgeniyyaskevich.finalproject.command.ActionCommand;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.CarType;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.User;
import by.epam.javaweb.evgeniyyaskevich.finalproject.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class FormOrderCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(FormOrderCommand.class);
    private static final int MINIVAN_COEF = 3;
    private static final int UNIVERSAL_COEF = 1;
    private static final int ELITE_COEF = 10;
    private static final int STANDARD_TARIFF = 1;
    //TODO: insert constants in database!

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ApplicationBuilder applicationBuilder = new ApplicationBuilder();

        User user = (User) session.getAttribute("user");
        String destination = request.getParameter("destination");
        String childSeat = request.getParameter("child_seat");
        String carType = request.getParameter("carType").toUpperCase();

        applicationBuilder.setClientId(user.getId())
                .setDestination(destination)
                .setChildSeat(Boolean.parseBoolean(childSeat))
                .setCarType(CarType.valueOf(carType))
                .setPrice(calculatePrice(destination, CarType.valueOf(carType)));

        session.setAttribute("application", applicationBuilder.build());
        return new ResourceManager("config").getProperty("path.page.order");
    }

    private int calculatePrice(String destination, CarType carType) {
        int coefficient = getCoefficient(carType);
        return destination.length() * STANDARD_TARIFF * coefficient;
    }

    private int getCoefficient(CarType carType) {
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

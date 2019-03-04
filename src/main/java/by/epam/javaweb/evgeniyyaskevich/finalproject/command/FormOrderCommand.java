package by.epam.javaweb.evgeniyyaskevich.finalproject.command;

import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.Application;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.CarType;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.User;
import by.epam.javaweb.evgeniyyaskevich.finalproject.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class FormOrderCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(FormOrderCommand.class);
    private final int MINIVAN_COEF = 3;
    private final int UNIVERSAL_COEF = 1;
    private final int ELITE_COEF = 10;
    private final int STANDARD_TARIFF = 1;

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Application application = new Application();

        User user = (User) session.getAttribute("user");
        application.setClientId(user.getId());

        String destination = request.getParameter("destination");
        application.setDestination(destination);

        String childSeat = request.getParameter("child_seat");
        application.setChildSeat(Boolean.parseBoolean(childSeat));

        String carType = request.getParameter("carType").toUpperCase();
        application.setCarType(CarType.valueOf(carType));

        application.setPrice(calculatePrice(destination, CarType.valueOf(carType)));

        session.setAttribute("application", application);
        return new ResourceManager("config").getProperty("path.page.order");
    }

    private int calculatePrice(String destination, CarType carType) {
        int coefficient = getCoefficient(carType);
        return destination.length() * STANDARD_TARIFF * coefficient;
    }

    private int getCoefficient(CarType carType) {
        switch (carType) {
            case MINIVAN: return MINIVAN_COEF;
            case UNIVERSAL: return UNIVERSAL_COEF;
            case ELITE: return ELITE_COEF;
            default: LOGGER.warn("Unknown case in switch.");
        }
        return 0;
    }
}

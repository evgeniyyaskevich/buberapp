package by.epam.javaweb.evgeniyyaskevich.finalproject.command.driver;

import by.epam.javaweb.evgeniyyaskevich.finalproject.command.ActionCommand;
import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.exception.PersistException;
import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.implementation.MySqlApplicationDao;
import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.implementation.MySqlCarDao;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.Application;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.ApplicationState;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.Car;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.User;
import by.epam.javaweb.evgeniyyaskevich.finalproject.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AcceptOrderCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(AcceptOrderCommand.class);
    private MySqlApplicationDao applicationDao = new MySqlApplicationDao();
    private MySqlCarDao carDao = new MySqlCarDao();

    @Override
    public String execute(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            List<Application> applications = new ArrayList<>();
            List<Car> cars = carDao.getByDriverId(user.getId());
            for (Car car : cars) {
                applications.addAll(applicationDao.getByCar(car));
            }

            int orderNumber = Integer.parseInt(request.getParameter("orderNumber"));
            List<Application> orders = applications.stream()
                    .filter(app -> app.getId() == orderNumber)
                    .collect(Collectors.toList());
            Application currentOrder = (orders.isEmpty()) ? null : orders.get(0);

            if (currentOrder != null) {
                currentOrder.setState(ApplicationState.ACCEPTED);
                applicationDao.update(currentOrder);
            } else {
                request.setAttribute("errorMessage", "Unknown order number");
            }
        } catch (PersistException e) {
            LOGGER.error(e);
        }
        return new ResourceManager("config").getProperty("path.page.main");
    }
}

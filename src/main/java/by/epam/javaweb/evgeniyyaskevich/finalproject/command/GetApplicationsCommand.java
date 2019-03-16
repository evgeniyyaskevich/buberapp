package by.epam.javaweb.evgeniyyaskevich.finalproject.command;

import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.exception.PersistException;
import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.implementation.MySqlApplicationDao;
import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.implementation.MySqlCarDao;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.AccessLevel;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.Application;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.Car;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.User;
import by.epam.javaweb.evgeniyyaskevich.finalproject.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

public class GetApplicationsCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(GetApplicationsCommand.class);
    private MySqlApplicationDao applicationDao = MySqlApplicationDao.getInstance();
    private MySqlCarDao carDao = MySqlCarDao.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        List<Application> applications = new ArrayList<>();
        try {
            if (user.getLevel() == AccessLevel.CLIENT) {
                applications = applicationDao.getByClientId(user.getId());
            } else if (user.getLevel() == AccessLevel.DRIVER) {
                List<Car> cars = carDao.getByDriverId(user.getId());
                Set<Application> applicationSet = new HashSet<>();
                for (Car car : cars) {
                    applicationSet.addAll(applicationDao.getByCar(car));
                }
                applications = new ArrayList<>(applicationSet);
                applications.sort(Comparator.comparing(Application::getDateTime).reversed());
            }
            request.setAttribute("applications", applications);
        } catch (PersistException e) {
            LOGGER.error("ApplicationDao error", e);
        }
        return new ResourceManager("config").getProperty("path.page.main");
    }
}

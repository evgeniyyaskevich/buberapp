package by.epam.javaweb.evgeniyyaskevich.finalproject.command.admin;

import by.epam.javaweb.evgeniyyaskevich.finalproject.command.ActionCommand;
import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.exception.PersistException;
import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.implementation.MySqlCarDao;
import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.implementation.MySqlUserDao;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.AccessLevel;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.Car;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.User;
import by.epam.javaweb.evgeniyyaskevich.finalproject.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetParametersForAdminPanelCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(GetParametersForAdminPanelCommand.class);
    private MySqlUserDao userDao = new MySqlUserDao();
    private MySqlCarDao carDao = new MySqlCarDao();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        try {
            if (user.getLevel() == AccessLevel.ADMIN) {
                List<User> users = userDao.getAll();
                List<Car> cars = carDao.getAll();
                request.setAttribute("cars", cars);
                request.setAttribute("users", users);
            }
        } catch (PersistException e) {
            LOGGER.error(e);
        }
        return new ResourceManager("config").getProperty("path.page.main");
    }
}

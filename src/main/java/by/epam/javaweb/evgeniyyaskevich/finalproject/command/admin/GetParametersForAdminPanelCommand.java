package by.epam.javaweb.evgeniyyaskevich.finalproject.command.admin;

import by.epam.javaweb.evgeniyyaskevich.finalproject.command.ActionCommand;
import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.exception.PersistException;
import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.implementation.MySqlBlackListDao;
import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.implementation.MySqlCarDao;
import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.implementation.MySqlUserDao;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.AccessLevel;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.BlackListRecord;
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
    private MySqlUserDao userDao = MySqlUserDao.getInstance();
    private MySqlCarDao carDao = MySqlCarDao.getInstance();
    private MySqlBlackListDao blackListDao = MySqlBlackListDao.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        try {
            if (user.getLevel() == AccessLevel.ADMIN) {
                List<User> users = userDao.getAll();
                List<Car> cars = carDao.getAll();
                List<BlackListRecord> blackListRecords = blackListDao.getAll();
                request.setAttribute("cars", cars);
                request.setAttribute("users", users);
                request.setAttribute("blackListRecords", blackListRecords);
            }
        } catch (PersistException e) {
            LOGGER.error("Dao problems: ", e);
        }
        return new ResourceManager("config").getProperty("path.page.main");
    }
}

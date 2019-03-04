package by.epam.javaweb.evgeniyyaskevich.finalproject.command;

import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.exception.PersistException;
import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.implementation.MySqlApplicationDao;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.Application;
import by.epam.javaweb.evgeniyyaskevich.finalproject.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MakeOrderCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(MakeOrderCommand.class);
    private MySqlApplicationDao applicationDao = new MySqlApplicationDao();

    @Override
    public String execute(HttpServletRequest request) {
        ResourceManager configManager = new ResourceManager("config");
        try {
            HttpSession session = request.getSession();
            Application application = (Application) session.getAttribute("application");
            applicationDao.insert(application);
            return configManager.getProperty("path.page.successOrder");
        } catch (PersistException e) {
            LOGGER.error(e);
            request.setAttribute("errorMessage", "Order wasn`t insert.");
            return configManager.getProperty("path.page.error");
        }
    }
}

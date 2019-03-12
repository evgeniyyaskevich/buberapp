package by.epam.javaweb.evgeniyyaskevich.finalproject.command.admin;

import by.epam.javaweb.evgeniyyaskevich.finalproject.builder.CarBuilder;
import by.epam.javaweb.evgeniyyaskevich.finalproject.command.ActionCommand;
import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.exception.PersistException;
import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.implementation.MySqlCarDao;
import by.epam.javaweb.evgeniyyaskevich.finalproject.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteCarCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(DeleteCarCommand.class);
    private MySqlCarDao carDao = new MySqlCarDao();

    @Override
    public String execute(HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter("car_id"));
        try {
            CarBuilder carBuilder = new CarBuilder();
            carBuilder.setId(id);
            carDao.delete(carBuilder.build());
        } catch (PersistException e) {
            LOGGER.error(e);
        }
        return new ResourceManager("config").getProperty("path.page.main");
    }
}

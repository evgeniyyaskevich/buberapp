package by.epam.javaweb.evgeniyyaskevich.finalproject.command.admin;

import by.epam.javaweb.evgeniyyaskevich.finalproject.builder.CarBuilder;
import by.epam.javaweb.evgeniyyaskevich.finalproject.command.ActionCommand;
import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.exception.PersistException;
import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.implementation.MySqlCarDao;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.CarType;
import by.epam.javaweb.evgeniyyaskevich.finalproject.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddCarCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(AddCarCommand.class);
    private MySqlCarDao carDao = MySqlCarDao.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        long driverId = Long.parseLong(request.getParameter("driver_id"));
        String carBrand = request.getParameter("car_brand");
        CarType type = CarType.valueOf(request.getParameter("car_type"));
        Boolean childSeat = Boolean.parseBoolean(request.getParameter("child_seat"));

        try {
            CarBuilder carBuilder = new CarBuilder();
            carBuilder.setDriverId(driverId)
                    .setBrand(carBrand)
                    .setType(type)
                    .setChildSeat(childSeat);
            carDao.insert(carBuilder.build());
        } catch (PersistException e) {
            LOGGER.error(e);
        }
        return new ResourceManager("config").getProperty("path.page.main");
    }
}

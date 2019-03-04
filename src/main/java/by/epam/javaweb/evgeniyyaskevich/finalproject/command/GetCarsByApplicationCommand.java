package by.epam.javaweb.evgeniyyaskevich.finalproject.command;

import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.exception.PersistException;
import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.implementation.MySqlCarDao;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.Application;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.Car;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.CarType;
import by.epam.javaweb.evgeniyyaskevich.finalproject.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

public class GetCarsByApplicationCommand implements ActionCommand {
    private static final Logger LOGGER =
            LogManager.getLogger(GetCarsByApplicationCommand.class);
    private MySqlCarDao carDao = new MySqlCarDao();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Application application = (Application) session.getAttribute("application");
        CarType carType = application.getCarType();
        Boolean childSeat = application.getChildSeat();

        List<Car> cars = null;
        try {
            cars = carDao.getAll()
                    .stream()
                    .filter(car -> car.getType() == carType
                            && car.getChildSeat() == childSeat)
                    .collect(Collectors.toList());
        } catch (PersistException e) {
            LOGGER.error(e);
        }
        request.setAttribute("cars", cars);
        return new ResourceManager("config").getProperty("path.page.order");
    }
}

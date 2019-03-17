package by.epam.javaweb.evgeniyyaskevich.finalproject.command.client;

import by.epam.javaweb.evgeniyyaskevich.finalproject.builder.ApplicationBuilder;
import by.epam.javaweb.evgeniyyaskevich.finalproject.command.ActionCommand;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.CarType;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.User;
import by.epam.javaweb.evgeniyyaskevich.finalproject.service.OrderService;
import by.epam.javaweb.evgeniyyaskevich.finalproject.util.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class FormOrderCommand implements ActionCommand {
    private OrderService orderService = OrderService.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ApplicationBuilder applicationBuilder = new ApplicationBuilder();
        User user = (User) session.getAttribute("user");
        String destination = request.getParameter("destination");
        String childSeat = request.getParameter("child_seat");
        String carType = request.getParameter("carType").toUpperCase();
        int price = orderService.calculatePrice(destination, CarType.valueOf(carType));

        applicationBuilder.setClientId(user.getId())
                .setDestination(destination)
                .setChildSeat(Boolean.parseBoolean(childSeat))
                .setCarType(CarType.valueOf(carType))
                .setPrice(price);

        session.setAttribute("application", applicationBuilder.build());
        return new ResourceManager("config").getProperty("path.page.order");
    }
}

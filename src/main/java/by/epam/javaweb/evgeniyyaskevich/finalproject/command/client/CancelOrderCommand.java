package by.epam.javaweb.evgeniyyaskevich.finalproject.command.client;

import by.epam.javaweb.evgeniyyaskevich.finalproject.command.ActionCommand;
import by.epam.javaweb.evgeniyyaskevich.finalproject.service.OrderService;
import by.epam.javaweb.evgeniyyaskevich.finalproject.util.ResourceManager;

import javax.servlet.http.HttpServletRequest;

public class CancelOrderCommand implements ActionCommand {
    private OrderService orderService = OrderService.getInstance();
    @Override
    public String execute(HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter("application_id"));
        orderService.cancelOrder(id);
        return new ResourceManager("config").getProperty("path.page.main");
    }
}

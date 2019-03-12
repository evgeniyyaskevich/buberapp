package by.epam.javaweb.evgeniyyaskevich.finalproject.command.admin;

import by.epam.javaweb.evgeniyyaskevich.finalproject.builder.UserBuilder;
import by.epam.javaweb.evgeniyyaskevich.finalproject.command.ActionCommand;
import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.exception.PersistException;
import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.implementation.MySqlUserDao;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.AccessLevel;
import by.epam.javaweb.evgeniyyaskevich.finalproject.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class UpdateUserCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(UpdateUserCommand.class);
    private MySqlUserDao userDao = new MySqlUserDao();

    @Override
    public String execute(HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter("user_id"));
        AccessLevel level = AccessLevel.valueOf(request.getParameter("level_access"));
        String name = request.getParameter("user_name");
        try {
            UserBuilder userBuilder = new UserBuilder();
            userBuilder.setId(id)
                        .setLevel(level)
                        .setName(name);
            userDao.update(userBuilder.build());
        } catch (PersistException e) {
            LOGGER.error(e);
        }
        return new ResourceManager("config").getProperty("path.page.main");
    }
}
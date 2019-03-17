package by.epam.javaweb.evgeniyyaskevich.finalproject.command.admin;

import by.epam.javaweb.evgeniyyaskevich.finalproject.command.ActionCommand;
import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.exception.PersistException;
import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.implementation.MySqlBlackListDao;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.BlackListRecord;
import by.epam.javaweb.evgeniyyaskevich.finalproject.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddUserToBlackListCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(AddUserToBlackListCommand.class);
    private MySqlBlackListDao blackListDao = MySqlBlackListDao.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter("user_id"));
        String reason = request.getParameter("reason");
        BlackListRecord record = new BlackListRecord(id, reason);
        try {
            blackListDao.insert(record);
        } catch (PersistException e) {
            LOGGER.error(e);
        }
        return new ResourceManager("config").getProperty("path.page.main");
    }
}

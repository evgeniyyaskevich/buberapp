package by.epam.javaweb.evgeniyyaskevich.finalproject.command.admin;

import by.epam.javaweb.evgeniyyaskevich.finalproject.command.ActionCommand;
import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.exception.PersistException;
import by.epam.javaweb.evgeniyyaskevich.finalproject.dao.implementation.MySqlBlackListDao;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.BlackListRecord;
import by.epam.javaweb.evgeniyyaskevich.finalproject.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteUserFromBlackListCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(DeleteUserFromBlackListCommand.class);
    private MySqlBlackListDao blackListDao = MySqlBlackListDao.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter("user_id"));
        BlackListRecord record = new BlackListRecord(id, "");
        try {
            blackListDao.delete(record);
        } catch (PersistException e) {
            LOGGER.error("BlackListDao problem: ", e);
        }
        return new ResourceManager("config").getProperty("path.page.main");
    }
}

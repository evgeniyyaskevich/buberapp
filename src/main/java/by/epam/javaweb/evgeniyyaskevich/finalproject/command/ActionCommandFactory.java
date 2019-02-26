package by.epam.javaweb.evgeniyyaskevich.finalproject.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ActionCommandFactory {
    private static final Logger LOGGER = LogManager.getLogger(ActionCommandFactory.class);

    private static final class SingletonHolder {
        private static final ActionCommandFactory INSTANCE = new ActionCommandFactory();
    }

    private ActionCommandFactory() {}

    public static ActionCommandFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand currentCommand = new EmptyCommand();
        String actionName = request.getParameter("command");

        try {
            CommandEnum currentEnum = CommandEnum.valueOf(actionName.toUpperCase());
            currentCommand = currentEnum.getCommand();
        } catch (IllegalArgumentException e) {
            //LOGGER.error()
            //TODO: processing
        }
        return currentCommand;
    }
}
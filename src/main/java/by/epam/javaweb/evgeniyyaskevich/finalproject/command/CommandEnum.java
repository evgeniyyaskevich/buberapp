package by.epam.javaweb.evgeniyyaskevich.finalproject.command;

import by.epam.javaweb.evgeniyyaskevich.finalproject.command.admin.*;
import by.epam.javaweb.evgeniyyaskevich.finalproject.command.client.CancelOrderCommand;
import by.epam.javaweb.evgeniyyaskevich.finalproject.command.client.FormOrderCommand;
import by.epam.javaweb.evgeniyyaskevich.finalproject.command.client.GetDestinationsCommand;
import by.epam.javaweb.evgeniyyaskevich.finalproject.command.client.MakeOrderCommand;
import by.epam.javaweb.evgeniyyaskevich.finalproject.command.driver.AcceptOrderCommand;

public enum CommandEnum {
    LOG_IN {
        {
            this.command = new LogInCommand();
        }
    },
    LOG_OUT {
        {
            this.command = new LogOutCommand();
        }
    },
    REGISTER {
        {
            this.command = new RegisterCommand();
        }
    },
    DELETE_USER {
        {
            this.command = new DeleteUserCommand();
        }
    },
    UPDATE_USER {
        {
            this.command = new UpdateUserCommand();
        }
    },
    ADD_USER_TO_BLACK_LIST {
        {
            this.command = new AddUserToBlackListCommand();
        }
    },
    DELETE_USER_FROM_BLACK_LIST {
        {
            this.command = new DeleteUserFromBlackListCommand();
        }
    },
    ADD_CAR {
        {
            this.command = new AddCarCommand();
        }
    },
    DELETE_CAR {
        {
            this.command = new DeleteCarCommand();
        }
    },
    FORM_ORDER {
        {
            this.command = new FormOrderCommand();
        }
    },
    UPDATE_DESTINATION {
        {
            this.command = new UpdateDestinationCommand();
        }
    },
    DELETE_DESTINATION {
        {
            this.command = new DeleteDestinationCommand();
        }
    },
    ADD_DESTINATION {
        {
            this.command = new AddDestinationCommand();
        }
    },
    GET_DESTINATIONS {
        {
            this.command = new GetDestinationsCommand();
        }
    },
    GET_APPLICATIONS {
        {
            this.command = new GetApplicationsCommand();
        }
    },
    GET_PARAMATERS_FOR_ADMIN {
        {
            this.command = new GetParametersForAdminPanelCommand();
        }
    },
    MAKE_ORDER {
        {
            this.command = new MakeOrderCommand();
        }
    },
    ACCEPT_ORDER {
        {
            this.command = new AcceptOrderCommand();
        }
    },
    CANCEL_ORDER {
        {
            this.command = new CancelOrderCommand();
        }
    },
    RETURN_MAIN {
        {
            this.command = new ReturnToMainCommand();
        }
    },
    SET_LANGUAGE {
        {
            this.command = new SetLanguageCommand();
        }
    },
    EMPTY {
        {
            this.command = new EmptyCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCommand() {
        return command;
    }
}
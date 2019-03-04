package by.epam.javaweb.evgeniyyaskevich.finalproject.command;

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
    FORM_ORDER {
        {
            this.command = new FormOrderCommand();
        }
    },
    GET_DESTINATIONS {
        {
            this.command = new GetDestinationsCommand();
        }
    },
    MAKE_ORDER {
        {
            this.command = new MakeOrderCommand();
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
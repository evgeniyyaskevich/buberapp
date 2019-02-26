package by.epam.javaweb.evgeniyyaskevich.finalproject.command;

public enum CommandEnum {
    LOGIN {
        {
            this.command = new LogInCommand();
        }
    },
    LOGOUT {
        {
            //this.command = new LogOutCommand();
        }
    },
    REGISTER {
        {
            this.command = new RegisterCommand();
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
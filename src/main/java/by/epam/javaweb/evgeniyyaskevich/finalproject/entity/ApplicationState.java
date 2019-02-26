package by.epam.javaweb.evgeniyyaskevich.finalproject.entity;

public enum ApplicationState {
    WAITING("waiting"),
    ACCEPTED("accepted");

    private String name;

    ApplicationState(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

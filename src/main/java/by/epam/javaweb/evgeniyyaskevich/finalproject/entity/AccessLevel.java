package by.epam.javaweb.evgeniyyaskevich.finalproject.entity;

public enum AccessLevel {
    CLIENT("client"),
    ADMIN("admin"),
    DRIVER("driver");

    private String name;

    AccessLevel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

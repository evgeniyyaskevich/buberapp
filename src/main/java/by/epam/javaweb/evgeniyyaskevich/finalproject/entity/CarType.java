package by.epam.javaweb.evgeniyyaskevich.finalproject.entity;

public enum CarType {
    UNIVERSAL("universal"),
    MINIVAN("minivan"),
    ELITE("elite");

    private String name;

    CarType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

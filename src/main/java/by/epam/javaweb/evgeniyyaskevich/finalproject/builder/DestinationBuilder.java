package by.epam.javaweb.evgeniyyaskevich.finalproject.builder;

import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.Destination;

public class DestinationBuilder {
    private long id;
    private String name;
    private Integer southCoord;
    private Integer northCoord;

    public DestinationBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public DestinationBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public DestinationBuilder setSouthCoord(Integer southCoord) {
        this.southCoord = southCoord;
        return this;
    }

    public DestinationBuilder setNorthCoord(Integer northCoord) {
        this.northCoord = northCoord;
        return this;
    }

    public Destination build() {
        Destination newDestination = new Destination();
        newDestination.setName(name);
        newDestination.setSouthCoord(southCoord);
        newDestination.setNorthCoord(northCoord);
        newDestination.setId(id);
        return newDestination;
    }
}

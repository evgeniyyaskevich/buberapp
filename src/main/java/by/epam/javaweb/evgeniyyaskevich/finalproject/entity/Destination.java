package by.epam.javaweb.evgeniyyaskevich.finalproject.entity;

public class Destination extends BaseEntity {
    private String name;
    private Integer southCoord;
    private Integer northCoord;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSouthCoord() {
        return southCoord;
    }

    public void setSouthCoord(Integer southCoord) {
        this.southCoord = southCoord;
    }

    public Integer getNorthCoord() {
        return northCoord;
    }

    public void setNorthCoord(Integer northCoord) {
        this.northCoord = northCoord;
    }
}

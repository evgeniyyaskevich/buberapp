package by.epam.javaweb.evgeniyyaskevich.finalproject.entity;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Destination that = (Destination) o;
        return name.equals(that.name) &&
                southCoord.equals(that.southCoord) &&
                northCoord.equals(that.northCoord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, southCoord, northCoord);
    }

    @Override
    public String toString() {
        return "Destination{" +
                "name='" + name + '\'' +
                ", southCoord=" + southCoord +
                ", northCoord=" + northCoord +
                '}';
    }
}

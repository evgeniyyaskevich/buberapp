package by.epam.javaweb.evgeniyyaskevich.finalproject.entity;

import java.util.Objects;

public class Car extends BaseEntity {
    private Long driverId;
    private String brand;
    private String year;
    private CarType type;
    private Boolean childSeat;

    public Car() {
        super();
    }

    public long getDriverId() {
        return driverId;
    }

    public void setDriverId(long driverId) {
        this.driverId = driverId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    public Boolean getChildSeat() {
        return childSeat;
    }

    public void setChildSeat(Boolean childSeat) {
        this.childSeat = childSeat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return driverId.equals(car.driverId) &&
                brand.equals(car.brand) &&
                year.equals(car.year) &&
                type == car.type &&
                childSeat.equals(car.childSeat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(driverId, brand, year, type, childSeat);
    }

    @Override
    public String toString() {
        return "Car{" +
                "driverId=" + driverId +
                ", brand='" + brand + '\'' +
                ", year='" + year + '\'' +
                ", type=" + type +
                ", childSeat=" + childSeat +
                '}';
    }
}

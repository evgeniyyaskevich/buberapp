package by.epam.javaweb.evgeniyyaskevich.finalproject.builder;

import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.Car;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.CarType;

public class CarBuilder {
    private long id;
    private long driverId;
    private String brand;
    private String year;
    private CarType type;
    private Boolean childSeat;

    public CarBuilder setDriverId(Long driverId) {
        this.driverId = driverId;
        return this;
    }

    public CarBuilder setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public CarBuilder setYear(String year) {
        this.year = year;
        return this;
    }

    public CarBuilder setType(CarType type) {
        this.type = type;
        return this;
    }

    public CarBuilder setChildSeat(Boolean childSeat) {
        this.childSeat = childSeat;
        return this;
    }

    public CarBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public Car build() {
        Car newCar = new Car();
        newCar.setId(id);
        newCar.setDriverId(driverId);
        newCar.setBrand(brand);
        newCar.setYear(year);
        newCar.setType(type);
        newCar.setChildSeat(childSeat);
        return newCar;
    }
}

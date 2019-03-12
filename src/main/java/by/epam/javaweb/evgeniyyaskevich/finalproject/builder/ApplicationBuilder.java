package by.epam.javaweb.evgeniyyaskevich.finalproject.builder;

import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.Application;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.ApplicationState;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.CarType;

import java.time.LocalDateTime;

public class ApplicationBuilder {
    private long clientId;
    private LocalDateTime dateTime;
    private String destination;
    private Integer price;
    private Boolean childSeat = false;
    private ApplicationState state = ApplicationState.WAITING;
    private CarType carType = CarType.UNIVERSAL;

    public ApplicationBuilder setClientId(long clientId) {
        this.clientId = clientId;
        return this;
    }

    public ApplicationBuilder setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public ApplicationBuilder setDestination(String destination) {
        this.destination = destination;
        return this;
    }

    public ApplicationBuilder setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public ApplicationBuilder setChildSeat(Boolean childSeat) {
        this.childSeat = childSeat;
        return this;
    }

    public ApplicationBuilder setState(ApplicationState state) {
        this.state = state;
        return this;
    }

    public ApplicationBuilder setCarType(CarType carType) {
        this.carType = carType;
        return this;
    }

    public Application build() {
        Application newApplication = new Application();
        newApplication.setClientId(clientId);
        newApplication.setDateTime(dateTime);
        newApplication.setDestination(destination);
        newApplication.setPrice(price);
        newApplication.setChildSeat(childSeat);
        newApplication.setState(state);
        newApplication.setCarType(carType);
        return newApplication;
    }
}

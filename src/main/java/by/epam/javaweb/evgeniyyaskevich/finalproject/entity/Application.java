package by.epam.javaweb.evgeniyyaskevich.finalproject.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Application extends BaseEntity {
    private Long clientId;
    private LocalDateTime dateTime;
    private String destination;
    private Integer price;
    private Boolean childSeat = false;
    private ApplicationState state = ApplicationState.WAITING;
    private CarType carType = CarType.UNIVERSAL;

    public Application() {
        super();
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public Boolean getChildSeat() {
        return childSeat;
    }

    public void setChildSeat(Boolean childSeat) {
        this.childSeat = childSeat;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public ApplicationState getState() {
        return state;
    }

    public void setState(ApplicationState state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Application that = (Application) o;
        return clientId == that.clientId &&
                dateTime.equals(that.dateTime) &&
                destination.equals(that.destination) &&
                price.equals(that.price) &&
                childSeat.equals(that.childSeat) &&
                state == that.state &&
                carType == that.carType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, dateTime, destination, price, childSeat, state, carType);
    }

    @Override
    public String toString() {
        return "Application{" +
                "clientId=" + clientId +
                ", dateTime=" + dateTime +
                ", destination='" + destination + '\'' +
                ", price=" + price +
                ", childSeat=" + childSeat +
                ", state=" + state +
                ", carType=" + carType +
                '}';
    }
}

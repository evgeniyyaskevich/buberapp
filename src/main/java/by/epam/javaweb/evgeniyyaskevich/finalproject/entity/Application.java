package by.epam.javaweb.evgeniyyaskevich.finalproject.entity;

import java.time.LocalDateTime;

public class Application extends BaseEntity {
    private long clientId;
    private LocalDateTime dateTime;
    private ApplicationState state;

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
}

package by.epam.javaweb.evgeniyyaskevich.finalproject.entity;

public class BaseEntity {
    private long id;

    BaseEntity() {}
    BaseEntity(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

package by.epam.javaweb.evgeniyyaskevich.finalproject.entity;

//TODO: equals() and hashCode() for all entities!
public class BlackListRecord extends BaseEntity {
    private String reason;

    public long getUserId() {
        return super.getId();
    }

    public void setUserId(long id) {
        super.setId(id);
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

package by.epam.javaweb.evgeniyyaskevich.finalproject.entity;

public class User extends BaseEntity {
    private String name;
    private String password;
    private AccessLevel level = AccessLevel.CLIENT;
    private Integer bonus = 0;

    public User() {}

    public Integer getBonus() {
        return bonus;
    }

    public void setBonus(Integer bonus) {
        this.bonus = bonus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccessLevel getLevel() {
        return level;
    }

    public void setLevel(AccessLevel level) {
        this.level = level;
    }
}

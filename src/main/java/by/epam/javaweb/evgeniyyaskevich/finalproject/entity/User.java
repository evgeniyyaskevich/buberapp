package by.epam.javaweb.evgeniyyaskevich.finalproject.entity;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return name.equals(user.name) &&
                password.equals(user.password) &&
                level == user.level &&
                bonus.equals(user.bonus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password, level, bonus);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", level=" + level +
                ", bonus=" + bonus +
                '}';
    }
}

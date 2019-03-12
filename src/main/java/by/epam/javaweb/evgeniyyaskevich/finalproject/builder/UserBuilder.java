package by.epam.javaweb.evgeniyyaskevich.finalproject.builder;

import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.AccessLevel;
import by.epam.javaweb.evgeniyyaskevich.finalproject.entity.User;

public class UserBuilder {
    private String name;
    private String password;
    private AccessLevel level = AccessLevel.CLIENT;
    private Integer bonus = 0;
    private long id;

    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserBuilder setLevel(AccessLevel level) {
        this.level = level;
        return this;
    }

    public UserBuilder setBonus(Integer bonus) {
        this.bonus = bonus;
        return this;
    }

    public UserBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public User build() {
        User newUser = new User();
        newUser.setName(name);
        newUser.setPassword(password);
        newUser.setLevel(level);
        newUser.setBonus(bonus);
        newUser.setId(id);
        return newUser;
    }
}

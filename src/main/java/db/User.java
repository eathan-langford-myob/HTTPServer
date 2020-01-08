package db;

import java.util.Objects;

public class User {
    private String name;
    private boolean admin;

    public User(String name, boolean admin) {
        this.name = name;
        this.admin = admin;
    }

    public User(String name) {
        this(name, false);
    }

    public String getName() {
        return name;
    }

    public boolean isAdmin() {
        return admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return admin == user.admin &&
                Objects.equals(getName(), user.getName());
    }

    public void setName(String name) {
        this.name = name;
    }
}

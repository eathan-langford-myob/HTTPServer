package db;

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

    public void setName(String name) {
        this.name = name;
    }
}
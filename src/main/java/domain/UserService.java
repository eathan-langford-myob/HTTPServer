package domain;

import db.User;
import db.UserDB;
import utilities.DBValidator;

public class UserService {
    private UserDB database;

    public UserService(UserDB database) {
this.database = database;
    }

    public String readAll() {
        return database.getAllDbEntries().toString();
    }

    public void createUser(String nameFromRequest) {
        User newUser = new User(nameFromRequest);
        database.addUser(newUser);
    }

    public User ReadByID(int IDFromPath) {
        return database.getUserByID(IDFromPath);
    }

    public boolean isUserInDB(int idFromPath) {
        return DBValidator.isUserInDatabase(database, idFromPath);
    }

    public void removeUserByID(int idFromPath) {
        database.deleteUserByID(idFromPath);
    }

    public void updateUserNameByID(int idFromPath, String nameFromRequest) {
        User queryUser = database.getUserByID(idFromPath);
        queryUser.setName(nameFromRequest);
    }

    public boolean isSameName(int queryID, String namesFromRequest) {
        String[] nameBreakdown = namesFromRequest.split(",");
        return ReadByID(queryID).getName().equalsIgnoreCase(nameBreakdown[0]);
    }
}

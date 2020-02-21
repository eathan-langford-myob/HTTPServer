package domain;

import db.User;
import db.UserDB;
import utilities.DBValidator;
import utilities.InvalidRequestException;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserService {
    private final UserDB database;
    private final ResourceBundle outputMessages;

    public UserService(UserDB database, ResourceBundle outputMessages) {
        this.database = database;
        this.outputMessages = outputMessages;
    }

    public String readAll() {
        return database.getAllDbEntries().toString();
    }

    public ArrayList<String> collectUserNames() {
        return database.getAllUsersNames();
    }


    public void createUser(String nameFromRequest) throws InvalidRequestException {
        if (!nameFromRequest.isEmpty()) {
            User newUser = new User(nameFromRequest);
            database.addUser(newUser);
        } else {
            throw new InvalidRequestException(outputMessages.getString("error_post_user"));
        }
    }

    public User ReadByID(int IDFromPath) throws InvalidRequestException {
        if (isUserInDB(IDFromPath)) {
            return database.getUserByID(IDFromPath);
        } else {
            throw new InvalidRequestException(outputMessages.getString("error_getting_user"));
        }
    }

    public boolean isUserInDB(int idFromPath) {
        return DBValidator.isUserInDatabase(database, idFromPath);
    }

    public void removeUserByID(int IDfromPath) throws InvalidRequestException {
        if (isUserEditable(IDfromPath)) {
            database.deleteUserByID(IDfromPath);
        } else {
            throw new InvalidRequestException(outputMessages.getString("error_delete_user"));
        }
    }

    public void updateUserNameByID(int IDfromPath, String nameFromRequest) throws InvalidRequestException {
        if (isUserEditable(IDfromPath)) {
            database.updateUserByID(IDfromPath, nameFromRequest);
        } else {
            throw new InvalidRequestException(outputMessages.getString("error_put_user"));
        }
    }

    private boolean isUserEditable(int IDfromPath) {
        return isUserInDB(IDfromPath) && !isUserAdmin(IDfromPath);
    }

    private boolean isUserAdmin(int ID) {
        return database.getUserByID(ID).isAdmin();
    }

}

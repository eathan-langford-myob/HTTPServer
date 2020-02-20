package domain;

import server.InvalidRequestException;
import db.User;
import db.UserDB;
import utilities.DBValidator;

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
            throw new InvalidRequestException("error_post_user");
        }
    }

    public User ReadByID(long IDFromPath) throws InvalidRequestException {
        if (isUserInDB(IDFromPath)) {
            return database.getUserByID(IDFromPath);
        } else {
            throw new InvalidRequestException("error_getting_user");
        }
    }

    public boolean isUserInDB(long idFromPath) {
        return DBValidator.isUserInDatabase(database, idFromPath);
    }

    public void removeUserByID(long IDfromPath) throws InvalidRequestException {
        if (isUserEditable(IDfromPath)) {
            database.deleteUserByID(IDfromPath);
        }else {
            throw new InvalidRequestException("error_delete_user");
        }
    }

    public void updateUserNameByID(long IDfromPath, String nameFromRequest) throws InvalidRequestException {
          if (isUserEditable(IDfromPath)) {
                  database.updateUserByID(IDfromPath, nameFromRequest);
          } else {
              throw new InvalidRequestException("error_put_user");
          }
    }
    private boolean isUserEditable(long IDfromPath) {
        return isUserInDB(IDfromPath) && !isUserAdmin(IDfromPath);
    }
    private boolean isUserAdmin(long ID) {
           return database.getUserByID(ID).isAdmin();
    }

}

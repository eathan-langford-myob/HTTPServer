package db;

import java.util.ArrayList;
import java.util.HashMap;

interface DB {
    HashMap<Long, User> users = new HashMap<Long, User>();
    long ID = 1;

    User getUserByID(long ID);

    void addUser(User user);

    ArrayList<String> getAllUsersNames();

    ArrayList<String> getAllDbEntries();

    void deleteUserByID(long ID);

    long getUserID(String name);

    void updateUserName(String queryName, String replacementName);
}

package db;

import java.util.ArrayList;

interface DB {

    User getUserByID(long ID);

    void addUser(User user);

    ArrayList<String> getAllUsersNames();

    ArrayList<String> getAllDbEntries();

    void deleteUserByID(long ID);

    void updateUserByID(long ID, String newName);
}

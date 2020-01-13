package db;

import java.util.ArrayList;

interface DB {
    long ID = 1;

    User getUserByID(long ID);

    void addUser(User user);

    ArrayList<String> getAllUsersNames();

    ArrayList<String> getAllDbEntries();

    void deleteUserByID(long ID);
}

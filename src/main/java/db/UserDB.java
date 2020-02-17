package db;

import java.util.ArrayList;
import java.util.HashMap;

public class UserDB implements DB {
    private HashMap<Long, User> users = new HashMap<>();
    private long ID = 1;

    public UserDB() {
    }

    @Override
    public User getUserByID(long ID) {
            return users.get(ID);
    }

    @Override
    public void addUser(User userName) {
        users.put(ID, userName);
        ID++;

    }

    @Override
    public ArrayList<String> getAllUsersNames() {
        ArrayList<String> collectNamesFromDB = new ArrayList<>();
        for (User user : users.values()) {
            collectNamesFromDB.add(user.getName());
        }
        return collectNamesFromDB;
    }

    @Override
    public ArrayList<String> getAllDbEntries() {
        ArrayList<String> collectEntriesFromDb = new ArrayList<>();
        users.forEach((ID, user)-> collectEntriesFromDb.add(ID+","+user.getName()));
        return collectEntriesFromDb;
    }

    @Override
    public void deleteUserByID(long ID) {
        users.remove(ID);
    }
}
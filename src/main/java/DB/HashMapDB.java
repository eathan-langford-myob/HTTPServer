package DB;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class HashMapDB implements DB {
    private HashMap<Long, Person> users = new HashMap<Long, Person>();
    private long ID = 1;

    public HashMapDB() {
        this.addUser(new Person("Eathan"));
    }

    @Override
    public Person getUserByID(long ID) {
        if (users.containsKey(ID)) {
            return users.get(ID);
        }
        return null;
    }

    @Override
    public void addUser(Person user) {
        users.put(ID, user);
        ID++;
    }

    private Collection<Person> getAllUsers() {
    return users.values();
    }

    @Override
    public ArrayList<String> getAllUsersNames() {
        ArrayList<String> collectNamesFromDB = new ArrayList<>();
        for (Person user : getAllUsers()) {
            collectNamesFromDB.add(user.getName());
        }
        return collectNamesFromDB;
    }


    @Override
    public void deleteUserByID(long ID) {
        if (users.get(ID) != null) {
            users.remove(ID);
        }
    }
}

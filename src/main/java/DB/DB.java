package DB;

import java.util.ArrayList;
import java.util.HashMap;

interface DB {
    HashMap<Long, Person> users = new HashMap<Long, Person>();
    long ID = 1;

    Person getUserByID(long ID);

    void addUser(Person user);

    ArrayList<String> getAllUsersNames();

    void deleteUserByID(long ID);
}

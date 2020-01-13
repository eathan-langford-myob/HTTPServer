package utilities;

import db.UserDB;

import java.util.ArrayList;

public class DBValidator {


    public static boolean hasMoreThanOneEntryInDB(ArrayList<String> collectionOfNamesFromDB) {
        return collectionOfNamesFromDB.size() > 1;
    }

    public static boolean isUserInDatabase(UserDB database, long ID) {
        if (database.getUserByID(ID) != null) {
            return true;
        }
        return false;
    }

}
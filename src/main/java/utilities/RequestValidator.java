package utilities;

import DB.HashMapDB;

import java.util.ArrayList;

public class RequestValidator {
    public static boolean isValidStringResponse(String response, HashMapDB database) {
        boolean isValidString = response.chars().allMatch(Character::isLetter);
        boolean isEmpty = response.isEmpty();
        boolean isValidDatabaseEntry = isUserInDatabase(database, response);
        return !isEmpty && isValidDatabaseEntry && isValidString;
    }

    public static boolean hasMoreThanOneEntryInDB(ArrayList<String> collectionOfNamesFromDB) {
        return collectionOfNamesFromDB.size() > 1;
    }

    public static boolean isSecondLastInDB(ArrayList<String> collectionOfNamesFromDB, String userName) {
        return collectionOfNamesFromDB.get(collectionOfNamesFromDB.size() - 2).equals(userName);
    }

    public static boolean isUserInDatabase(HashMapDB database, String queryName) {
        database.getUserByName(queryName);
        ArrayList<String> names = database.getAllUsersNames();
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equalsIgnoreCase(queryName)) {
                return true;
            }
        }
        return false;
    }
}

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

    public static boolean isValidUserRequest(String path, UserDB DB) {
        return HttpRequestValidator.isValidIdRequest(path) && isUserInDatabase(DB, HttpUtils.getIdFromPath(path)) && !isAdmin(path, DB);
    }

    public static boolean isAdmin(String path, UserDB DB) {
        return DB.getUserByID(HttpUtils.getIdFromPath(path)).isAdmin();
    }
}
package utilities;

import db.UserDB;

import java.util.ArrayList;

public class HttpRequestValidator {

    public static boolean isValidIdRequest(String urlRequest) {
        String[] requestBreakdown = urlRequest.split(Constants.users_endpoint);
        boolean doesEndWithID = requestBreakdown[1].replaceAll("/", "").chars().allMatch(Character::isDigit);
        return urlRequest.startsWith(Constants.users_endpoint) && doesEndWithID;
    }

    public static boolean isAllUsersEndpoint(String path) {
        return path.endsWith(Constants.users_endpoint) || path.equalsIgnoreCase(Constants.users_endpoint + "/");
    }

    public static boolean isIdEndpoint(String path, String endpoint) {
        return path.startsWith(endpoint) && isValidIdRequest(path);
    }

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
        return isValidIdRequest(path) && isUserInDatabase(DB, HttpUtils.getIdFromPath(path)) && !isAdmin(path, DB);
    }

    public static boolean isAdmin(String path, UserDB DB) {
        return DB.getUserByID(HttpUtils.getIdFromPath(path)).getName().equalsIgnoreCase(Constants.admin_name);
    }
}

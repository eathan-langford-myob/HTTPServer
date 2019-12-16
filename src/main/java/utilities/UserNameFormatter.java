package utilities;

import DB.HashMapDB;

import java.util.ArrayList;

public class UserNameFormatter {
    private static String capitalize(String str) {
        if(str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

     public static String collectNames(HashMapDB database) {
        ArrayList<String> collectionOfNamesFromDB = database.getAllUsersNames();
        return formatUsersToString(collectionOfNamesFromDB);
    }

    public static String formatUsersToString(ArrayList<String> collectionOfNamesFromDB) {
        StringBuilder collectNamesForGreeting = new StringBuilder();

        for (String userName : collectionOfNamesFromDB) {
            collectNamesForGreeting.append(capitalize(userName));
            if (RequestValidator.hasMoreThanOneEntryInDB(collectionOfNamesFromDB) &&
                    RequestValidator.isSecondLastInDB(collectionOfNamesFromDB, userName)) {
                collectNamesForGreeting.append(" and ");
            } else {
                collectNamesForGreeting.append(", ");
            }
        }
        return collectNamesForGreeting.toString();
    }
}

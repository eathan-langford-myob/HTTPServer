package utilities;

import java.util.ArrayList;

public class UserStringFormatter {
    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }


    private static boolean isSecondLastInDB(ArrayList<String> collectionOfNamesFromDB, String userName) {
        return collectionOfNamesFromDB.get(collectionOfNamesFromDB.size() - 2).equals(userName);
    }

    public static String formatUsersToString(ArrayList<String> collectionOfNamesFromDB) {
        StringBuilder collectNamesForGreeting = new StringBuilder();


        for (String userName : collectionOfNamesFromDB) {
            collectNamesForGreeting.append(capitalize(userName));
            if (DBValidator.hasMoreThanOneEntryInDB(collectionOfNamesFromDB) &&
                    isSecondLastInDB(collectionOfNamesFromDB, userName)) {
                collectNamesForGreeting.append(" and ");
            } else {
                collectNamesForGreeting.append(", ");
            }
        }
        return collectNamesForGreeting.toString();
    }
}
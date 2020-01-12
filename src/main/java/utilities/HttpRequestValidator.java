package utilities;

public class HttpRequestValidator {

    public static boolean isValidIdRequest(String urlRequest) {
        String[] requestBreakdown = urlRequest.split("/users");
        boolean doesEndWithID = requestBreakdown[1].replaceAll("/", "").chars().allMatch(Character::isDigit);
        return urlRequest.startsWith("/users") && doesEndWithID;
    }

    public static boolean isAllUsersEndpoint(String path) {
        return path.endsWith("/users") || path.equalsIgnoreCase("/users" + "/");
    }

    public static boolean isValidUpdateRequest(String urlRequest) {
        return urlRequest.contains(",") && urlRequest.split(",").length==2;
    }
}
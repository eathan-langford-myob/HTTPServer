package utilities;

public class HttpRequestValidator {

    public static boolean isValidIdRequest(String urlRequest) {
        String[] requestBreakdown = urlRequest.split(System.getenv("USER_ENDPOINT"));
        boolean doesEndWithID = requestBreakdown[1].replaceAll("/", "").chars().allMatch(Character::isDigit);
        return urlRequest.startsWith(System.getenv("USER_ENDPOINT")) && doesEndWithID;
    }

    public static boolean isAllUsersEndpoint(String path) {
        return path.endsWith(System.getenv("USER_ENDPOINT")) || path.equalsIgnoreCase(System.getenv("USER_ENDPOINT") + "/");
    }

    public static boolean isIdEndpoint(String path, String endpoint) {
        return path.startsWith(endpoint) && isValidIdRequest(path);
    }
}
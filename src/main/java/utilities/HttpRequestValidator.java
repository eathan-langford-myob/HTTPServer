package utilities;

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
}
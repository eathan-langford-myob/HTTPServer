package utilities;

import java.io.InputStream;
import java.util.Scanner;

public class HttpUtils {

    public static Integer getIdFromPath(String path) {
        return Integer.parseInt(path.split("/users")[1].replace("/", ""));
    }

    public static String getRequestFromBody(InputStream requestBody) {
        Scanner response = new Scanner(requestBody).useDelimiter("\\A");
        return response.hasNext() ? response.next() : "";
    }
}
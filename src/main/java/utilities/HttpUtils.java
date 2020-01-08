package utilities;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class HttpUtils {
    public static void writeResponse(HttpExchange exchange, String response, int statusCode) throws IOException {
        exchange.sendResponseHeaders(statusCode, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public static Integer getIdFromPath(String path) {
        return Integer.parseInt(path.split(System.getenv("USER_ENDPOINT"))[1].replace("/", ""));
    }

    public static String getRequestFromBody(InputStream requestBody) {
        Scanner response = new Scanner(requestBody).useDelimiter("\\A");
        return response.hasNext() ? response.next() : "";
    }
}
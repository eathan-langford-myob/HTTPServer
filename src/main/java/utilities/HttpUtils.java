package utilities;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class HttpUtils {

    public static Integer getIdFromPath(String path) {
        return Integer.parseInt(path.split("/users")[1].replace("/", ""));
    }

    public static String getRequestFromBody(InputStream requestBody) {
        Scanner response = new Scanner(requestBody).useDelimiter("\\A");
        return response.hasNext() ? response.next() : "";
    }

    public static void writeResponse(int statusCode, HttpExchange exchange, String response) throws IOException {
        exchange.sendResponseHeaders(statusCode, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
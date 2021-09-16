package httpServer.Server;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import httpServer.Resources.DataBase;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ProjectHttpHandler implements HttpHandler {
    private static final String GET_METHOD = "GET";
    private static final Charset CHARSET = StandardCharsets.UTF_8;
    private static final int STATUS_OK = 200;
    private static final int METHOD_NOT_ALLOWED = 405;
    private static final int FORBIDDEN_REQUEST = 403;

    public ProjectHttpHandler() {}

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println(exchange.getRequestURI().toString());
        if (exchange.getRequestURI().toString().equals("/")) {
            generateMainPage(exchange);
        } else if (exchange.getRequestURI().toString().equals("/schedule")) {
            generateSchedulePage(exchange);
        } else if ("GET".equals(exchange.getRequestMethod())) {
            OutputStream outputstream = exchange.getResponseBody();
            //TODO JSON response
            if (exchange.getRequestURI().getQuery() != null) {
                String response = getQuery(exchange).get("name");
                exchange.sendResponseHeaders(200, response.length());
                outputstream.write(response.getBytes());
            } else {
                exchange.sendResponseHeaders(200, 3);
                outputstream.write("aaa".getBytes(StandardCharsets.UTF_8));
            }
            outputstream.flush();
            outputstream.close();
        } else {
            exchange.sendResponseHeaders(FORBIDDEN_REQUEST, -1);
        }
    }

    private Map<String, String> getQuery(HttpExchange exchange) {
        String query = exchange.getRequestURI().getQuery();
        String[] arg = query.split("=");
        Map<String,String> ret = new HashMap<>();
        ret.put(arg[0], arg[1]);
        return ret;
    }

//    private String generateResponseBody(HttpExchange exchange) {
//        final Headers headers = exchange.getResponseHeaders();
//        headers.set("Content-Type", String.format("application/json,; charset=%s", CHARSET));
//        final String responseBody
//    }

    private void generateMainPage(HttpExchange exchange) throws IOException {
        DataBase db = DataBase.getDb();
        final Headers headers = exchange.getResponseHeaders();
        headers.set("Content-Type", String.format("application/json,; charset=%s", CHARSET));
        final StringBuilder responseBody = new StringBuilder();
        responseBody.append("TOP RATED MOVIES TODAY\n\n");
        for (String name : db.getMovies().keySet()) {
            responseBody.append(db.getMovieByName(name).getName());
            responseBody.append("\n");
        }
        final byte[] rawResponse = responseBody.toString().getBytes(CHARSET);
        exchange.sendResponseHeaders(STATUS_OK, rawResponse.length);
        OutputStream out = exchange.getResponseBody();
        out.write(rawResponse);
        out.flush();
        out.close();
        System.out.printf("status code %4d, %8d bytes send\n", STATUS_OK, rawResponse.length);
    }

    private void generateSchedulePage(HttpExchange exchange) throws IOException {
        DataBase db = DataBase.getDb();
        final Headers headers = exchange.getResponseHeaders();
        headers.set("Content-Type", String.format("application/json,; charset=%s", CHARSET));
        final byte[] rawResponse = ("SCHEDULE\n\n" + db.getSchedule().getSchedule()).getBytes(CHARSET);
        exchange.sendResponseHeaders(STATUS_OK, rawResponse.length);
        OutputStream out = exchange.getResponseBody();
        out.write(rawResponse);
        out.flush();
        out.close();
        System.out.printf("status code %4d, %8d bytes send\n", STATUS_OK, rawResponse.length);
    }
}

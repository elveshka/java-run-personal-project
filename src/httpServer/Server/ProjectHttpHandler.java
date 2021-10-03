package httpServer.Server;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import httpServer.Resources.DataBase;
import httpServer.Resources.Movie;
import httpServer.Resources.Session;

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
    private static final int PAGE_NOT_FOUND = 404;
    private final Session session;

    public ProjectHttpHandler(Session session) {
        this.session = session;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (GET_METHOD.equals(exchange.getRequestMethod())) {
            if (exchange.getRequestURI().toString().equals("/")) {
                generateMainPage(exchange);
            } else if (exchange.getRequestURI().toString().equalsIgnoreCase("/schedule")) {
                generateSchedulePage(exchange);
            } else if (exchange.getRequestURI().toString().equalsIgnoreCase("/purchase")) {
                generatePurchasePage(exchange);
            } else if (exchange.getHttpContext().getPath().equalsIgnoreCase("/movies/search")) {
                generateMovieTitlePage(exchange);
            } else {
                exchange.sendResponseHeaders(PAGE_NOT_FOUND, -1);
            }
        } else {
            exchange.sendResponseHeaders(METHOD_NOT_ALLOWED, -1);
        }
    }

    private void generateMainPage(HttpExchange exchange) throws IOException {
        DataBase db = DataBase.getDb();
        final Headers headers = exchange.getResponseHeaders();
        headers.set("Content-Type", String.format("application/json,; charset=%s", CHARSET));
        final StringBuilder responseBody = new StringBuilder();
        responseBody.append("{");
        for (String name : db.getMovies().keySet()) {
            responseBody.append(String.format("{\"movie_name\":\"%s\"},", db.getMovieByName(name).getName()));
        }
        responseBody.append("}");
        sendResponseBody(exchange, responseBody.toString().getBytes(CHARSET));
    }

    private void generateSchedulePage(HttpExchange exchange) throws IOException {
        final Headers headers = exchange.getResponseHeaders();
        headers.set("Content-Type", String.format("application/json,; charset=%s", CHARSET));
        sendResponseBody(exchange, session.getSchedule().getJsonResponseToString().getBytes(CHARSET));
    }

    private void generateMovieTitlePage(HttpExchange exchange) throws IOException {
        // temporary solution for one get variable
        Map.Entry<String, String> name = getQueryToMap(exchange.getRequestURI().getQuery()).entrySet().iterator().next();
        if (name.getKey().equalsIgnoreCase("name")) {
            DataBase db = DataBase.getDb();
            final Movie movie = db.getMovieByName(name.getValue().toLowerCase());
            if (movie != null) {
                final Headers headers = exchange.getResponseHeaders();
                headers.set("Content-Type", String.format("application/json,; charset=%s", CHARSET));
                sendResponseBody(exchange, movie.getTitleToJsonResponseToString().getBytes(CHARSET));
            }
        }
        exchange.sendResponseHeaders(PAGE_NOT_FOUND, -1);
    }

    private void generatePurchasePage(HttpExchange exchange) throws IOException {
        if (!session.validateGetRequest(getQueryToMap(exchange.getRequestURI().getQuery()))) {
            exchange.sendResponseHeaders(PAGE_NOT_FOUND, -1);
        } else {

        }
    }

    private Map<String, String> getQueryToMap(String query) {
        if (query == null || query.isEmpty()) {
            return null;
        }
        final Map<String, String> result = new HashMap<>();
        for (String param : query.split("&")) {
            String[] entry = param.split("=");
            if (entry.length > 1) {
                result.put(entry[0], entry[1]);
            } else {
                result.put(entry[0], "");
            }
        }
        return result;
    }

    private void sendResponseBody(HttpExchange exchange, byte[] rawResponse) throws IOException {
        exchange.sendResponseHeaders(STATUS_OK, rawResponse.length);
        OutputStream out = exchange.getResponseBody();
        out.write(rawResponse);
        out.flush();
        out.close();
        System.out.printf("status code %4d, %8d bytes send\n", STATUS_OK, rawResponse.length);
    }
}

//    OutputStream outputstream = exchange.getResponseBody();
//            if (exchange.getRequestURI().getQuery() != null) {
//                    String response = getQuery(exchange).get("name");
//                    exchange.sendResponseHeaders(200, response.length());
//                    outputstream.write(response.getBytes());
//                    } else {
//                    exchange.sendResponseHeaders(200, 3);
//                    outputstream.write("aaa".getBytes(StandardCharsets.UTF_8));
//                    }
//                    outputstream.flush();
//                    outputstream.close();
package httpServer.Server;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import httpServer.Resources.DayProgramming;
import httpServer.Resources.Movie;

import java.io.IOException;
import java.util.Map;

public class MoviesSearchPageHandler extends ProjectHttpHandler {
    private final DayProgramming dayProgramming;

    public MoviesSearchPageHandler(DayProgramming dayProgramming) {
        this.dayProgramming = (dayProgramming);
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (GET_METHOD.equals(exchange.getRequestMethod())) {
            Map.Entry<String, String> name = getParamsToMap(exchange.getRequestURI().getQuery()).entrySet().iterator().next();
            if (name.getKey().equalsIgnoreCase("name")) {
                final Movie movie = dayProgramming.getMovieByName(name.getValue().toLowerCase());
                if (movie != null) {
                    final Headers headers = exchange.getResponseHeaders();
                    headers.set("Content-Type", String.format("application/json; CHARSET_UTF_8=%s", UTF_8));
                    sendResponseBody(exchange, dayProgramming.getMovieTitleToJson(movie).getBytes(UTF_8));
                }
            }
            exchange.sendResponseHeaders(PAGE_NOT_FOUND, -1);
        }
        exchange.sendResponseHeaders(METHOD_NOT_ALLOWED, -1);
    }
}

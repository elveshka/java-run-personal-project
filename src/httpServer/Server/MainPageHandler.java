package httpServer.Server;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import httpServer.Resources.DayProgramming;

import java.io.IOException;

public class MainPageHandler extends ProjectHttpHandler {
    private final DayProgramming dayProgramming;

    public MainPageHandler(DayProgramming dayProgramming) {
        this.dayProgramming = (dayProgramming);
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (GET_METHOD.equals(exchange.getRequestMethod())) {
            final Headers headers = exchange.getResponseHeaders();
            headers.set("Content-Type", String.format("application/json; CHARSET_UTF_8=%s", UTF_8));
            sendResponseBody(exchange, dayProgramming.getTopMoviesToJson().getBytes(UTF_8));
            exchange.sendResponseHeaders(PAGE_NOT_FOUND, -1);
        }
        exchange.sendResponseHeaders(METHOD_NOT_ALLOWED, -1);
    }
}

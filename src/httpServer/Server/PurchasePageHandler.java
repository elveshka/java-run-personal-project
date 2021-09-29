package httpServer.Server;

import com.sun.net.httpserver.HttpExchange;
import httpServer.Resources.DayProgramming;

import java.io.IOException;
import java.util.Map;

public class PurchasePageHandler extends ProjectHttpHandler {
    private final DayProgramming dayProgramming;

    public PurchasePageHandler(DayProgramming dayProgramming) {
        this.dayProgramming = (dayProgramming);
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (POST_METHOD.equals(exchange.getRequestMethod())) {
            Map<String, String> post = getParamsToMap(new String(exchange.getRequestBody().readAllBytes()));
            if (post.size() == 3 && post.containsKey("seat") &&
                    post.containsKey("hall") && post.containsKey("time")) {
                try {
                    int time = Integer.parseInt(post.get("time"));
                    dayProgramming.getHallByName(post.get("hall")).getSchedule().getTickets().get(time).chooseSeat(post.get("seat"));
                    sendResponseBody(exchange, "{\"success\": true}\n".getBytes(UTF_8));
                } catch (RuntimeException e) {
                    sendResponseBody(exchange, e.getMessage().getBytes(UTF_8));
                }
            }
            exchange.sendResponseHeaders(PAGE_NOT_FOUND, -1);
        }
        exchange.sendResponseHeaders(METHOD_NOT_ALLOWED, -1);
    }
}

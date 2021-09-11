package httpServer.Server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class ProjectHttpHandler implements HttpHandler {
    public ProjectHttpHandler() {}

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        OutputStream outputstream = exchange.getResponseBody();
        if ("GET".equals(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(200, 6);
            outputstream.write("sobaka".getBytes());
            outputstream.flush();
            outputstream.close();
        }
    }
}

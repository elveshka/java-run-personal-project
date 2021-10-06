package httpServer.Server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public abstract class ProjectHttpHandler implements HttpHandler {
    protected static final String GET_METHOD = "GET";
    protected static final String POST_METHOD = "POST";
    protected static final Charset UTF_8 = StandardCharsets.UTF_8;
    protected static final int STATUS_OK = 200;
    protected static final int METHOD_NOT_ALLOWED = 405;
    protected static final int PAGE_NOT_FOUND = 404;

    protected Map<String, String> getParamsToMap(String params) {
        if (params == null || params.isEmpty()) {
            return null;
        }
        final Map<String, String> result = new HashMap<>();
        for (String param : params.split("&")) {
            String[] entry = param.split("=");
            if (entry.length > 1) {
                result.put(entry[0], entry[1]);
            } else {
                result.put(entry[0], "");
            }
        }
        return result;
    }

    protected void sendResponseBody(HttpExchange exchange, byte[] rawResponse) throws IOException {
        exchange.sendResponseHeaders(STATUS_OK, rawResponse.length);
        OutputStream out = exchange.getResponseBody();
        out.write(rawResponse);
        out.flush();
        System.out.printf("status code %4d, %8d bytes send\n", STATUS_OK, rawResponse.length);
    }
}
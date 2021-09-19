package httpServer.Server;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {

    private static final String mainPage = "/";
    private static final String schedulePage = "/schedule";
    private static final String moviesSearchPage = "/movies/search";

    public static void main(String[] args) {
        HttpServer httpserver;
        ProjectHttpHandler handler = new ProjectHttpHandler();
        try {
            httpserver = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);
            httpserver.createContext(mainPage, handler);
            httpserver.createContext(schedulePage, handler);
            httpserver.createContext(moviesSearchPage, handler);
            httpserver.start();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}

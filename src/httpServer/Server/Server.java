package httpServer.Server;

import com.sun.net.httpserver.HttpServer;
import httpServer.Resources.Session;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {

    private static final String mainPage = "/";
    private static final String schedulePage = "/schedule";
    private static final String moviesSearchPage = "/movies/search";
    private static final String purchasePage = "/purchase";

    public static void main(String[] args) {
        HttpServer httpserver;
        Session todaySession = new Session("today");
        ProjectHttpHandler handler = new ProjectHttpHandler(todaySession);
        try {
            httpserver = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);
            httpserver.createContext(mainPage, handler);
            httpserver.createContext(schedulePage, handler);
            httpserver.createContext(moviesSearchPage, handler);
            httpserver.createContext(purchasePage, handler);
            httpserver.start();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}

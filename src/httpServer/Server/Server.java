package httpServer.Server;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

import httpServer.Resources.DataBase;
import httpServer.Resources.Movie;

public class Server {
    public static void main(String[] args) {
        HttpServer httpserver;
        ProjectHttpHandler handler = new ProjectHttpHandler();
        try {
            httpserver = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);
            httpserver.createContext("/", handler);
            httpserver.createContext("/movies/search", handler);
            httpserver.start();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}

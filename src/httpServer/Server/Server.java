package httpServer.Server;

import com.sun.net.httpserver.HttpServer;
import httpServer.Resources.DayProgramming;
import httpServer.Resources.Hall;
import httpServer.Resources.Movie;
import httpServer.Resources.Schedule;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Set;

public class Server {

    private static final String mainPage = "/";
    private static final String moviesSearchPage = "/movies/search";
    private static final String ticketsPage = "/tickets";
    private static final String purchasePage = "/purchase";

    public static void main(String[] args) {
        HttpServer httpserver;
        System.out.println(System.getProperty("file.encoding"));
        DayProgramming todayDayProgramming = new DayProgramming(hardcodeBlock());
        ProjectHttpHandler handler = new ProjectHttpHandler(todayDayProgramming);
        try {
            httpserver = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);
            httpserver.createContext(mainPage, handler);
            httpserver.createContext(purchasePage, handler);
            httpserver.createContext(moviesSearchPage, handler);
            httpserver.createContext(ticketsPage, handler);
            httpserver.start();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private static Set<Hall> hardcodeBlock() {
        Set<Hall> halls = new HashSet<>();
        Hall a1 = new Hall("A1", 10);
        Hall b1 = new Hall("B1", 7);
        Schedule schedule1 = new Schedule();
        Schedule schedule2 = new Schedule();
        Movie titanic = new Movie("Titanic", 1997, "James Cameron");
        Movie austin = new Movie("Austin Powers", 2002, "Jay Roach");
        Movie green = new Movie("Green Elephant", 1999, "Svetlana Baskova");
        schedule1.addSession(13, titanic, a1.getHallSize());
        schedule1.addSession(17, austin, a1.getHallSize());
        schedule1.addSession(21, green, a1.getHallSize());

        schedule2.addSession(14, green, b1.getHallSize());
        schedule2.addSession(17, austin, b1.getHallSize());
        schedule2.addSession(19, titanic, b1.getHallSize());

        a1.setSchedule(schedule1);
        b1.setSchedule(schedule2);

        halls.add(a1);
        halls.add(b1);

        return halls;
    }
}

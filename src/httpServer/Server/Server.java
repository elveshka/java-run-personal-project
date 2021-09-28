package httpServer.Server;

import com.sun.net.httpserver.HttpServer;
import httpServer.Resources.Hall;
import httpServer.Resources.Movie;
import httpServer.Resources.Schedule;
import httpServer.Resources.DayProgramming;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Set;

public class Server {

    private static final String mainPage = "/";
    private static final String schedulePage = "/schedule";
    private static final String moviesSearchPage = "/movies/search";
    private static final String purchasePage = "/purchase";

    public static void main(String[] args) {
        HttpServer httpserver;

        DayProgramming todayDayProgramming = new DayProgramming("today", hardcodeBlock());
        ProjectHttpHandler handler = new ProjectHttpHandler(todayDayProgramming);
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

    private static Set<Hall> hardcodeBlock() {
        Set<Hall> halls = new HashSet<>();
        Hall a1 = new Hall("A1");
        Hall b1 = new Hall("B1");
        Hall a2 = new Hall("A2");
        Schedule schedule1 = new Schedule();
        Schedule schedule2 = new Schedule();
        Schedule schedule3 = new Schedule();
        Movie titanic = new Movie("Titanic", 1997, "James Cameron");
        Movie austin = new Movie("Austin Powers", 2002, "Jay Roach");
        Movie green = new Movie("Green Elephant", 1999, "Svetlana Baskova");
        schedule1.addSession(13, titanic);
        schedule1.addSession(17, austin);
        schedule1.addSession(21, green);

        schedule2.addSession(14, green);
        schedule2.addSession(17, austin);
        schedule2.addSession(19, titanic);

        schedule3.addSession(8, green);
        schedule3.addSession(11, green);
        schedule3.addSession(14, green);
        schedule3.addSession(17, green);
        schedule3.addSession(20, green);
        schedule3.addSession(23, green);

        a1.setSchedule(schedule1);
        b1.setSchedule(schedule2);
        a2.setSchedule(schedule3);

        halls.add(a1);
        halls.add(b1);
        halls.add(a2);

        return halls;
    }
}

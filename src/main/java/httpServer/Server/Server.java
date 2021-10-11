package httpServer.Server;

import com.sun.net.httpserver.HttpServer;
import httpServer.Resources.DayProgramming;
import httpServer.Resources.Hall;
import httpServer.Resources.Movie;
import httpServer.Resources.Schedule;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;

public class Server {
    public static final Logger logger = Logger.getLogger(
            Server.class.getName());
    private static final String mainPage = "/";
    private static final String moviesSearchPage = "/movies/search";
    private static final String ticketsPage = "/tickets";
    private static final String purchasePage = "/purchase";
    private static HttpServer httpserver;

    public static void main(String[] args) throws IOException {
        try {
            httpserver = HttpServer.create(new InetSocketAddress("localhost", Integer.parseInt(args[0])), 0);
        } catch (NumberFormatException e) {
            System.out.printf("Wrong port format %s", args[0]);
        }
        DayProgramming todayDayProgramming = new DayProgramming(hardcodeBlock());

        MainPageHandler mainPageHandler = new MainPageHandler(todayDayProgramming);
        MoviesSearchPageHandler moviesSearchPageHandler = new MoviesSearchPageHandler(todayDayProgramming);
        TicketsPageHandler ticketsPageHandler = new TicketsPageHandler(todayDayProgramming);
        PurchasePageHandler purchasePageHandler = new PurchasePageHandler(todayDayProgramming);

        httpserver.createContext(mainPage, mainPageHandler);
        httpserver.createContext(moviesSearchPage, moviesSearchPageHandler);
        httpserver.createContext(purchasePage, purchasePageHandler);
        httpserver.createContext(ticketsPage, ticketsPageHandler);
        httpserver.start();

        logger.info(String.format("Server start on port %s\n%s",
                args[0],
                System.getProperty("file.encoding")));
    }

    public static Set<Hall> hardcodeBlock() {
        Set<Hall> halls = new LinkedHashSet<>();
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

    public static HttpServer getHttpserver() {
        return httpserver;
    }
}

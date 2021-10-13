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
    private static final int defaultPort = 8080;

    private final HttpServer httpServer;
    private final int port;
    private MainPageHandler mainPageHandler;
    private MoviesSearchPageHandler moviesSearchPageHandler;
    private TicketsPageHandler ticketsPageHandler;
    private PurchasePageHandler purchasePageHandler;
    private DayProgramming todayDayProgramming = null;

    public Server(int port) {
        try {
            httpServer = HttpServer.create(
                    new InetSocketAddress("localhost", port), 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.port = port;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            new Server(defaultPort);
        } else {
            try {
                int port = Integer.parseInt(args[0]);
                new Server(port);
            } catch (NumberFormatException e) {
                logger.warning(String.format("Wrong port format %s", args[0]));
                throw new RuntimeException(e);
            }
        }
    }

    private static Set<Hall> hardcodeBlock() {
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

    public void startServer() {
        if (todayDayProgramming == null) {
            todayDayProgramming = new DayProgramming(hardcodeBlock());
        }

        mainPageHandler = new MainPageHandler(todayDayProgramming);
        moviesSearchPageHandler = new MoviesSearchPageHandler(todayDayProgramming);
        ticketsPageHandler = new TicketsPageHandler(todayDayProgramming);
        purchasePageHandler = new PurchasePageHandler(todayDayProgramming);

        createContextToServer(httpServer);

        httpServer.start();
        logger.info(String.format("Server start on port %s\nSystem encoding: %s",
                port,
                System.getProperty("file.encoding")));
    }

    private void createContextToServer(HttpServer httpserver) {
        httpserver.createContext(mainPage, mainPageHandler);
        httpserver.createContext(moviesSearchPage, moviesSearchPageHandler);
        httpserver.createContext(purchasePage, purchasePageHandler);
        httpserver.createContext(ticketsPage, ticketsPageHandler);
    }

    public HttpServer getHttpServer() {
        return httpServer;
    }

    public DayProgramming getTodayDayProgramming() {
        return todayDayProgramming;
    }

    public void setTodayDayProgramming(DayProgramming dayProgramming) {
        this.todayDayProgramming = dayProgramming;
    }
}

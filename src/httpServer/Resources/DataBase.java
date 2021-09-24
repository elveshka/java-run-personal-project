package httpServer.Resources;

import java.util.HashMap;
import java.util.Map;

/**
 * Simulating database queries
 */

public class DataBase {
    private final Map<String, Movie> movies = new HashMap<>();
    private final Schedule scheduleToday = new Schedule();
    private static final DataBase db = new DataBase(); // Singletone :D

    private final Hall hallA1 = new Hall("A1");
    private final Hall hallA2 = new Hall("A2");
    private final Hall hallB1 = new Hall("B1");

    private DataBase() {
        this.movies.put("titanic", new Movie("Titanic", 1997, "James Cameron"));
        this.movies.put("austin powers", new Movie("Austin Powers", 2002, "Jay Roach"));
        this.movies.put("green elephant", new Movie("Green Elephant", 1999, "Svetlana Baskova"));
    }

    public Movie getMovieByName(String name) {
        return db.movies.get(name.toLowerCase());
    }

    public static DataBase getDb() {
        return db;
    }

    public Map<String, Movie> getMovies() {
        return movies;
    }

    public Schedule getSchedule() {
        return scheduleToday;
    }

    public Hall getHallA1() {
        return hallA1;
    }

    public Hall getHallA2() {
        return hallA2;
    }

    public Hall getHallB1() {
        return hallB1;
    }
}

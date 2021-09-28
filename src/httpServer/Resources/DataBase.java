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

    private final Map<String, Hall> halls = new HashMap<>();

    private DataBase() {
        this.movies.put("titanic",        new Movie("Titanic", 1997, "James Cameron"));
        this.movies.put("austin powers",  new Movie("Austin Powers", 2002, "Jay Roach"));
        this.movies.put("green elephant", new Movie("Green Elephant", 1999, "Svetlana Baskova"));
        this.halls.put("A1", new Hall("A1"));
        this.halls.put("B1", new Hall("B1"));
        this.halls.put("A2", new Hall("A2"));
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

    public Hall getHallByName(String name) {
        return db.halls.get(name.toUpperCase());
    }
}

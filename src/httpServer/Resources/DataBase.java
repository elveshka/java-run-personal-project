package httpServer.Resources;

import java.util.HashMap;
import java.util.Map;

/**
 * Simulate database queries
 */

public class DataBase {
    private Map<String, Movie> movies = new HashMap<>();
    private Schedule schedule = new Schedule();
    private static DataBase db = null; // Singletone :D

    private DataBase() {
        this.movies.put("Titanic", new Movie("Titanic", 1997, "James Cameron"));
        this.movies.put("Austin Powers", new Movie("Austin Powers", 2002, "Jay Roach"));
        this.movies.put("Green Elephant", new Movie("Green Elephant", 1999, "Svetlana Baskova"));
    }

    public static Movie getMovieByName(String name) {
        if (db == null) {
            db = new DataBase();
        }
        return db.movies.get(name);
    }

    public static DataBase getDb() {
        if (db == null) {
            db = new DataBase();
        }
        return db;
    }

    public Map<String, Movie> getMovies() {
        if (db == null) {
            db = new DataBase();
        }
        return movies;
    }

    public Schedule getSchedule() {
        return schedule;
    }
}

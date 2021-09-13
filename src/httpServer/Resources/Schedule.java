package httpServer.Resources;

import java.util.HashMap;
import java.util.Map;

// WOOOOOOOOO HARCODE COMING ! ! !
public class Schedule {
    private static final Map<Integer, Movie> sessions = new HashMap<>();
    private Schedule() {
        for (int i = 0; i < 23; ++i) {
            sessions.put(i, null);
        }
        sessions.put(13, DataBase.getMovieByName("Titanic"));
        sessions.put(17, DataBase.getMovieByName("Austin Powers"));
        sessions.put(19, DataBase.getMovieByName("Green Elephant"));
    }
}

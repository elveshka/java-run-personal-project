package httpServer.Resources;

import java.util.HashMap;
import java.util.Map;

// WOOOOOOOOO HARCODE COMING ! ! !
public class Schedule {
    private static final Map<Integer, Movie> sessions = new HashMap<>();
    public Schedule() {
        for (int i = 0; i < 24; ++i) {
            sessions.put(i, null);
        }
        sessions.put(13, DataBase.getMovieByName("Titanic"));
        sessions.put(17, DataBase.getMovieByName("Austin Powers"));
        sessions.put(19, DataBase.getMovieByName("Green Elephant"));
    }

    public String getSchedule() {
        StringBuilder schedule = new StringBuilder();
        for (int time : sessions.keySet()) {
            if (sessions.get(time) != null) {
                schedule.append(String.format("%d:00 - %s\n", time, sessions.get(time).getName()));
            }
        }
        return schedule.toString();
    }

    public void setSessions(int time, Movie movie) {}
}

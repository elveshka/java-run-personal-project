package httpServer.Resources;

import java.util.HashMap;
import java.util.Map;

// WOOOOOOOOO HARCODE COMING ! ! !
public class Schedule {
    private static final Map<Integer, Movie> sessions = new HashMap<>();
    public Schedule() {
        DataBase db = DataBase.getDb();
        for (int i = 0; i < 24; ++i) {
            sessions.put(i, null);
        }
        sessions.put(13, db.getMovieByName("Titanic"));
        sessions.put(17, db.getMovieByName("Austin Powers"));
        sessions.put(19, db.getMovieByName("Green Elephant"));
    }

    public String getScheduleToString() {
        StringBuilder schedule = new StringBuilder();
        for (int time : sessions.keySet()) {
            if (sessions.get(time) != null) {
                schedule.append(String.format("%d:00 - %s\n", time, sessions.get(time).getName()));
            }
        }
        return schedule.toString();
    }

    public String getMovieSession(int time) {
        return sessions.get(time).getTitle();
    }
    public void setSessions(int time, Movie movie) {}
}

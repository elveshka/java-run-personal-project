package httpServer.Resources;

import java.util.HashMap;
import java.util.Map;

// WOOOOOOOOO HARCODE COMING ! ! !
public class Schedule {
    private static final Map<Integer, Movie> sessions = new HashMap<>();
    private static final Map<Integer, Seats> seats = new HashMap<>();
    public Schedule() {
        DataBase db = DataBase.getDb();
        for (int i = 0; i < 24; ++i) {
            sessions.put(i, null);
        }
        sessions.put(13, db.getMovieByName("Titanic"));
        sessions.put(17, db.getMovieByName("Austin Powers"));
        sessions.put(19, db.getMovieByName("Green Elephant"));
        seats.put(13, new Seats());
        seats.put(17, new Seats());
        seats.put(19, new Seats());
    }

    public String getSchedule() {
        StringBuilder schedule = new StringBuilder();
        for (int time : sessions.keySet()) {
            if (sessions.get(time) != null) {
                schedule.append(String.format("%d:00 - %s\n",
                        time,
                        sessions.get(time).getName()));
            }
        }
        return schedule.toString();
    }

    public String getJsonResponseToString() {
        StringBuilder str = new StringBuilder();
        str.append("{");
        for (int time : sessions.keySet()) {
            if (sessions.get(time) != null) {
                str.append(String.format("{\"session_time\":\"%d:00\",\"movie_name\":\"%s\"},",
                        time,
                        sessions.get(time).getName()));
            }
        }
        str.append("}");
        return str.toString();
    }

    public String getAvailableSeatsToString(int time) {
        return getSeatsOnTime(time).printSeatsToString();
    }

    public Seats getSeatsOnTime(int time) {
        return seats.get(time);
    }

    public void setSessions(int time, Movie movie) {}
}

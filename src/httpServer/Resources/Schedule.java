package httpServer.Resources;

import java.util.HashMap;
import java.util.Map;

// WOOOOOOOOO HARCODE COMING ! ! !
public class Schedule {
    private static final Map<Integer, Hall> sessions = new HashMap<>();
    private static final Map<Integer, Movie> movieOnTime = new HashMap<>();

    public Schedule() {
        for (int i = 0; i < 24; ++i) {
            sessions.put(i, null);
        }
    }

    public String getSchedule() {
        StringBuilder schedule = new StringBuilder();
        for (int time : sessions.keySet()) {
            if (sessions.get(time) != null) {
                schedule.append(String.format("%d:00 - %s\n",
                        time,
                        movieOnTime.get(time).getName()));
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
                        movieOnTime.get(time).getName()));
            }
        }
        str.append("}");
        return str.toString();
    }

    public String getAvailableSeatsToString(int time) {
        return getSeatsOnTime(time).printSeatsToString();
    }

    public Hall getSeatsOnTime(int time) {
        return sessions.get(time);
    }

    public Integer getMovieSessionTime(String movieName) {
        for (Integer time : movieOnTime.keySet()) {
            if (time != null && movieOnTime.get(time).getName().equalsIgnoreCase(movieName)) {
                return time;
            }
        }
        return null;
    }
    public Hall getHallByMovie(String movieName) {
        for (Integer time : movieOnTime.keySet()) {
            if (time != null && movieOnTime.get(time).getName().equals(movieName)) {
                return sessions.get(time);
            }
        }
        return null;
    }
    public void setSession(DataBase db) {
        sessions.put(13, db.getHallA1());
        sessions.put(17, db.getHallA2());
        sessions.put(19, db.getHallB1());
        movieOnTime.put(13, db.getMovieByName("Titanic"));
        movieOnTime.put(17, db.getMovieByName("Austin Powers"));
        movieOnTime.put(19, db.getMovieByName("Green Elephant"));
    }
}

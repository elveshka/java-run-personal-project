package httpServer.Resources;

import java.util.HashMap;
import java.util.Map;

public class Schedule {
    private final Map<Integer, Movie> sessions = new HashMap<>();

    public Schedule() {
        for (int i = 0; i < 24; ++i) {
            sessions.put(i, null);
        }
    }

    public void addSession(int time, Movie movie) {
        if (time >= 0 && time <= 23) {
            sessions.putIfAbsent(time, movie);
        }
    }

    public Map<Integer, Movie> getSessions() {
        return sessions;
    }

//    public String getSchedule() {
//        StringBuilder schedule = new StringBuilder();
//        for (int time : sessions.keySet()) {
//            if (sessions.get(time) != null) {
//                schedule.append(String.format("%d:00 - %s\n",
//                        time,
//                        movieOnTime.get(time).getName()));
//            }
//        }
//        return schedule.toString();
//    }
//
//    public String getJsonResponseToString() {
//        StringBuilder str = new StringBuilder();
//        str.append("{");
//        for (int time : sessions.keySet()) {
//            if (sessions.get(time) != null) {
//                str.append(String.format("{\"session_time\":\"%d:00\",\"movie_name\":\"%s\"},",
//                        time,
//                        movieOnTime.get(time).getName()));
//            }
//        }
//        str.append("}");
//        return str.toString();
//    }
//
//    public String getAvailableSeatsToString(int time) {
//        return getHallOnTime(time).printSeatsToString();
//    }
//
//    public Hall getHallOnTime(int time) {
//        return sessions.get(time);
//    }
//
//    public Integer getMovieSessionTime(String movieName) {
//        for (Integer time : movieOnTime.keySet()) {
//            if (time != null && movieOnTime.get(time).getName().equalsIgnoreCase(movieName)) {
//                return time;
//            }
//        }
//        return null;
//    }
//
//    public void setSession(DataBase db) {
//        sessions.put(13, db.getHallByName("A1"));
//        sessions.put(17, db.getHallByName("A2"));
//        sessions.put(19, db.getHallByName("B1"));
//        movieOnTime.put(13, db.getMovieByName("Titanic"));
//        movieOnTime.put(17, db.getMovieByName("Austin Powers"));
//        movieOnTime.put(19, db.getMovieByName("Green Elephant"));
//    }
}

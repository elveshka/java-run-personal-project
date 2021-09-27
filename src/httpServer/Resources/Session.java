package httpServer.Resources;

import java.util.Map;

//this class creating schedule for all halls depends on date/time
//but not now :D
public class Session {
    private final String sessionDate; // should be Date type
    private final Schedule schedule = new Schedule();

    public Session(String sessionDate) {
        this.sessionDate = sessionDate;
        schedule.setSession(DataBase.getDb());
    }

    public String getSessionDate() {
        return sessionDate;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public boolean validateGetRequest(Map<String, String> query) {
        String hall_name = "";
        String time = "";

        for (String s : query.keySet()) {
            switch (s) {
                case "time":
                    time = query.get(s);
                    break;
                case "hall_name":
                    hall_name = query.get(s);
                    break;
//                case "movie_name":
//                    movie_name = query.get(s);
//                    break;
            }
        }

        if (!time.isEmpty()  && !hall_name.isEmpty()) {
            if (schedule.getHallByMovie(movie_name).getHallName().equals(hall_name)) {
                if (schedule.getHallOnTime(Integer.parseInt(time)).getHallName().equals(hall_name)) {
                    return schedule.getMovieSessionTime(movie_name).toString().equals(time);
                }
            }
        }

        public String generatePurchasePageToJson(Ma)
        return false;
    }
}

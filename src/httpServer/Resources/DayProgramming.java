package httpServer.Resources;

import java.util.HashSet;
import java.util.Set;

public class DayProgramming {
    private final String date; // should be Date type
    private final Schedule schedule = new Schedule();
    private final Set<Hall> halls = new HashSet<>();
    private Set<Movie> topMovies = new HashSet<>();

    public DayProgramming(String date, Set<Hall> halls) {
        this.date = date;
        this.halls.addAll(halls);
        for (Hall hall : halls) {
            for (Integer time : hall.getSchedule().getSessions().keySet()) {
                if (hall.getSchedule().getSessions().get(time) != null) {
                    topMovies.add(hall.getSchedule().getSessions().get(time));
                }
            }
        }
    }

    public String getDate() {
        return date;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public Set<Hall> getHalls() {
        return halls;
    }

    public Movie getMovieByName(String name) {
        Movie movie;
        while (topMovies.iterator().hasNext()) {
            movie = topMovies.iterator().next();
            if (name.equalsIgnoreCase(movie.getName())) {
                return movie;
            }
        }
        return null;
    }

    public String getTopMoviesToJsonString() {
        StringBuilder response = new StringBuilder();

        response.append("{\n");
        for (Movie movie : topMovies) {
            response.append("\"movie_name\": \"").append(movie.getName())
                    .append("\",\n");
        }
        response.append("}");
        return response.toString();
    }
    //    public boolean validateGetRequest(Map<String, String> query) {
//        String hall_name = "";
//        String movie_name = "";
//        String time = "";
//
//        for (String s : query.keySet()) {
//            switch (s) {
//                case "time":
//                    time = query.get(s);
//                    break;
//                case "hall_name":
//                    hall_name = query.get(s);
//                    break;
////                case "movie_name":
////                    movie_name = query.get(s);
////                    break;
//            }
//        }
//
//        if (!time.isEmpty() && !hall_name.isEmpty()) {
//            if (schedule.getHallByMovie(movie_name).getHallName().equals(hall_name)) {
//                if (schedule.getHallOnTime(Integer.parseInt(time)).getHallName().equals(hall_name)) {
//                    return schedule.getMovieSessionTime(movie_name).toString().equals(time);
//                }
//            }
//        }
//    }
}

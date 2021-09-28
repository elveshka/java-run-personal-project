package httpServer.Resources;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DayProgramming {
    private final String date; // should be Date type
    private final Schedule schedule = new Schedule();
    private final Set<Hall> halls = new HashSet<>();
    private final Set<Movie> topMovies = new HashSet<>();

    public DayProgramming(String date, Set<Hall> halls) {
        this.date = date;
        this.halls.addAll(halls);
        for (Hall hall : this.halls) {
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
        for (Movie topMovie : topMovies) {
            System.out.println(topMovie.getName());
            if (name.equalsIgnoreCase(topMovie.getName())) {
                return topMovie;
            }
        }
        return null;
    }

    public String getTopMoviesToJson() {
        StringBuilder response = new StringBuilder();

        response.append("{\n");
        for (Movie movie : topMovies) {
            response.append("\"movie_name\": \"").append(movie.getName())
                    .append("\",\n");
        }
        response.append("}");
        return response.toString();
    }

    public String getMovieTitleToJson(Movie movie) {
        StringBuilder response = new StringBuilder();
        Map<Integer, Movie> ses;
        response.append(movie.getTitleToJsonString());
        response.append("{\n");
        for (Hall hall : halls) {
            ses = hall.getSchedule().getSessions();
            for (int time : ses.keySet()) {
                if (ses.get(time) != null) {
                    if (ses.get(time).getName().equals(movie.getName())) {
                        response.append("\t\"hall_name\": \"")
                                .append(hall.getHallName()).append("\",")
                                .append("\"time\": \"")
                                .append(time).append(":00\"\n");
                    }
                }
            }
            response.append("},\n");
        }
        return response.toString();
    }

    public boolean validateTicketsGetRequest(Map<String, String> query) {
        for (Hall hall : halls) {
            if (hall.getHallName().equalsIgnoreCase(query.get("hall"))) {
                int time = Integer.parseInt(query.get("time"));
                if (hall.getSchedule().getSessions().get(time) != null) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getTicketsToJson(Map<String, String> query) {
        int time = Integer.parseInt(query.get("time"));
        for (Hall hall : halls) {
            if (hall.getHallName().equalsIgnoreCase(query.get("hall"))) {
                return "{\"available_tickets\": \"" +
                        hall.getSchedule().getTickets().get(time).getTickets() + "\"},\n" +
                        hall.getSchedule().getTickets().get(time).printSeatsToString();
            }
        }
        return null;
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

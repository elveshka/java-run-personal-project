package httpServer.Resources;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DayProgramming {
    private final Set<Hall> halls = new HashSet<>();
    private final Set<Movie> topMovies = new HashSet<>();

    public DayProgramming(Set<Hall> halls) {
        this.halls.addAll(halls);
        for (Hall hall : this.halls) {
            for (Integer time : hall.getSchedule().getSessions().keySet()) {
                if (hall.getSchedule().getSessions().get(time) != null) {
                    topMovies.add(hall.getSchedule().getSessions().get(time));
                }
            }
        }
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

    public Hall getHallByName(String name) {
        for (Hall hall : halls) {
            if (hall.getHallName().equalsIgnoreCase(name)) {
                return hall;
            }
        }
        return null;
    }

    public String getTopMoviesToJson() {
        String response = "{\n";
        for (Movie movie : topMovies) {
            response = String.join("",
                    response,
                    String.format("\t\"movie_name\": \"%s\",\n", movie.getName()));
        }
        response = String.join("", response, "}\n");
        return response;
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

    public String getTicketsToJson(Map<String, String> query) {
        int time = Integer.parseInt(query.get("time"));
        for (Hall hall : halls) {
            if (hall.getHallName().equalsIgnoreCase(query.get("hall"))) {
                if (hall.getSchedule().getSessions().get(time) != null) {
                    return "{\"available_tickets\": \"" +
                            hall.getSchedule().getTickets().get(time).getTickets() + "\"},\n" +
                            hall.getSchedule().getTickets().get(time).printSeatsToString();
                }
            }
        }
        return null;
    }
}

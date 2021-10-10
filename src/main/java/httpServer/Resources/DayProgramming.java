package httpServer.Resources;

import java.util.*;


public class DayProgramming {
    private final Set<Hall> halls = new LinkedHashSet<>();
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
        Map<Integer, Movie> session;

        response.append(movie.getTitleToJsonString());
        response.append("{\n");

        for (Hall hall : halls) {
            session = hall.getSchedule().getSessions();
            for (int time : session.keySet()) {
                if (session.get(time) != null) {
                    if (session.get(time).getName().equals(movie.getName())) {
                        response.append(String.format("\t\"hall_name\": \"%s\",\"time\": \"%d:00\"\n",
                                hall.getHallName(), time));
                    }
                }
            }
            response.append("},\n");
        }

        return response.toString();
    }

    public String getTicketsToJson(Map<String, String> query) {
        Integer time = Integer.parseInt(query.get("time"));
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

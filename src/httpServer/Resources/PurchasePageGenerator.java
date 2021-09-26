package httpServer.Resources;

import java.util.Map;

public class PurchasePageGenerator {
    private String hall_name = "";
    private String movie_name = "";
    private String time = "";
    private boolean complete = false;

    public PurchasePageGenerator(String session, Map<String, String> query) {
        StringBuilder response = new StringBuilder();
        response.append("{")
                .append("\"session:\"").append(session).append("\",");
        query.forEach((k, v) -> {
            switch (k) {
                case "time":
                    this.time = v;
                    break;
                case "hall_name":
                    this.hall_name = v;
                    break;
                case "movie_name":
                    this.movie_name = v;
                    break;
            }
        });
        if (!time.isEmpty() && !movie_name.isEmpty() && !hall_name.isEmpty()) {
            DataBase db = DataBase.getDb();
            if (db.getMovieByName(movie_name) != null) {
                response.append("\"movie_name:")
                        .append(movie_name).append("\",");
                if (db.getHallByName(hall_name) != null) {
                    response.append("\"hall_name:")
                            .append(hall_name.toUpperCase()).append("\",");
                    if (db.getSchedule().getHallByMovie(movie_name).getHallName().equals(hall_name)
                            && db.getSchedule().getMovieSessionTime(movie_name).toString().equals(time)) {
                        response.append("\"time:")
                                .append(time).append("\",");

                    }
                }
            }
        }
    }

    public boolean isComplete() {
        return complete;
    }
}

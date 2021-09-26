package httpServer.Resources;

public class Movie {
    private String name;
    private int year;
    private String director;

    public Movie(String name, int year, String director) {
        this.name = name;
        this.year = year;
        this.director = director;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getTitleToJsonResponseToString() {
        DataBase db = DataBase.getDb();
        String str = "{" +
                "\"" + "movie_name" + "\"" +
                ":" +
                "\"" + getName() + "\"" + "," +
                "\"" + "year" + "\"" +
                ":" +
                "\"" + getYear() + "\"" + "," +
                "\"" + "director" + "\"" +
                ":" +
                "\"" + getDirector() + "\"" + "," +
                "\"" + "session_time" + "\"" +
                ":" +
                "\"" + db.getSchedule().getMovieSessionTime(this.name) + "\"" + "," +
                "\"" + "hall_name" + "\"" +
                ":" +
                "\"" + db.getSchedule().getHallByMovie(this.name).getHallName() + "\"" + "," +
                "\"" + "available_tickets" + "\"" +
                ":" +
                "\"" + db.getSchedule().getHallByMovie(this.name).getVacantSeats() + "\"" + "," +
                "}";
        return str;
    }
}

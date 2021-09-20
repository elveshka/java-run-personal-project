package httpServer.Resources;

public class Movie {
    private String name;
    private int year;
    private String director;
    private final DataBase db = DataBase.getDb();
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
        StringBuilder str = new StringBuilder();
        str.append("{")
                .append("\"")    .append("movie_name")           .append("\"")
                .append(":")        
                .append("\"")    .append(getName())              .append("\"").append(",")
                .append("\"")    .append("year")                 .append("\"")
                .append(":")        
                .append("\"")    .append(getYear())              .append("\"").append(",")
                .append("\"")    .append("director")             .append("\"")
                .append(":")        
                .append("\"")    .append(getDirector())          .append("\"").append(",")
                .append("\"")    .append("session_time")         .append("\"")
                .append(":")
                .append("\"")    .append(db.getSchedule().getMovieSessionTime(this.name))    .append("\"").append(",")
                .append("\"")    .append("hall_name")            .append("\"")
                .append(":")
                .append("\"")    .append(db.getSchedule().getHallByMovie(this.name).getHallName())     .append("\"").append(",")
                .append("\"")    .append("available_tickets")    .append("\"")
                .append(":")
                .append("\"")    .append(db.getSchedule().getHallByMovie(this.name).getVacantSeats())    .append("\"").append(",")
                .append("}");
        return str.toString();
    }
}

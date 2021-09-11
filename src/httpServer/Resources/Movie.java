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
}

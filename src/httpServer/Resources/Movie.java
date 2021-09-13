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

    public String getTitle() {
        return String.format("Name: %s\nYear: %d\nDirector: %s\n",
                getName(), getYear(), getDirector());
    }
}

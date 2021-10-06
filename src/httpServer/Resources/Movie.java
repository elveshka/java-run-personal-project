package httpServer.Resources;

public class Movie {
    private final String name;
    private final int year;
    private final String director;

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

    public String getTitleToJsonString() {
        String str = "{\n" +
                "\t\"movie_name\": \"" +
                getName() + "\",\n" +
                "\t\"year\": \"" +
                getYear() + "\",\n" +
                "\t\"director\": \"" +
                getDirector() + "\",\n" +
                "},\n";
        return str;
    }
}

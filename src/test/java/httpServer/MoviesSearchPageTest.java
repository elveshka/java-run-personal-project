package httpServer;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import httpServer.Resources.DayProgramming;
import httpServer.Resources.Hall;
import httpServer.Resources.Movie;
import httpServer.Resources.Schedule;
import httpServer.Server.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class MoviesSearchPageTest {

    private HttpUrl testUrl;
    private Server sutServer;
    private DayProgramming testProgramming;

    private void initDataBase(Hall testHall, Movie testMovie, int time) {
        Set<Hall> halls = new LinkedHashSet<>();
        Schedule testSchedule = new Schedule();
        testSchedule.addSession(time, testMovie, testHall.getHallSize());
        testHall.setSchedule(testSchedule);
        halls.add(testHall);
        testProgramming = new DayProgramming(halls);
    }

    private int generatePort() {
        Random random = new Random();
        int port;

        for (; ; ) {
            port = random.nextInt(30000) + 1024;
            try {
                new ServerSocket(port)
                        .close();
                break;
            } catch (IOException ignored) {
            }
        }
        return port;
    }

    private Response buildAndExecuteRequest(String testMovie) {
        String url = testUrl
                .newBuilder()
                .addQueryParameter("name", testMovie)
                .build()
                .toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        OkHttpClient client = new OkHttpClient();
        try {
            return client.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Before
    public void setUp() {

        int port = generatePort();
        sutServer = new Server(port);
        testUrl = HttpUrl
                .parse(String.format("http://localhost:%d/movies/search", port));
    }

    @After
    public void shutdownServer() {
        sutServer.getHttpServer().stop(0);
    }

    @Test
    public void shouldTestJsonResponse() throws IOException {
        //given
        String testMovie = "green elephant";
        initDataBase(
                new Hall("A1", 10),
                new Movie(testMovie, 1999, "someone"),
                21);
        sutServer.setTodayDayProgramming(testProgramming);
        sutServer.startServer();

        //when
        Response response = buildAndExecuteRequest(testMovie);

        Movie movie = sutServer.getTodayDayProgramming().getMovieByName(testMovie);
        String actual = sutServer.getTodayDayProgramming().getMovieTitleToJson(movie);

        String expected = response.body().string();
        response.body().close();

        assertThat(actual).isEqualTo(expected);
    }
}

package httpServer;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import httpServer.Resources.DayProgramming;
import httpServer.Resources.Movie;
import httpServer.Server.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Random;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

public class MoviesSearchPageTest {

    private static DayProgramming dayProgramming;
    private HttpUrl testUrl;
    private Server sutServer;
//    @BeforeClass
//    public static void initDataBase() {
//        dayProgramming = new DayProgramming(Server.hardcodeBlock());
//    }

    @Before
    public void generatePort() {
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

        sutServer = new Server(port);

        testUrl = HttpUrl
                .parse(String.format("http://localhost:%d/movies/search", port));
    }

    @After
    public void shutdownServer() {
        sutServer.getHttpServer().stop(0);
    }
    
    @Test
    public void shouldTestGreenElephantMovie() throws IOException {
        String testMovie = "green elephant";

        String url = testUrl
                .newBuilder()
                .addQueryParameter("name", testMovie)
                .build()
                .toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();

        Movie movie = dayProgramming.getMovieByName(testMovie);
        String actual = dayProgramming.getMovieTitleToJson(movie);

        String expected = response.body().string();
        response.body().close();

        assertThat(actual).isEqualTo(expected);
    }
}

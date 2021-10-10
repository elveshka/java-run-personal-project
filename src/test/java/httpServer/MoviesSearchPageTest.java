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
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class MoviesSearchPageTest {

    private static DayProgramming dayProgramming;
    private HttpUrl testUrl;

    @BeforeClass
    public static void initDataBase() {
        dayProgramming = new DayProgramming(Server.hardcodeBlock());
    }

    @Before
    public void generatePort() throws IOException {
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
        Server.main(new String[]{
                Integer.toString(port)
        });
        testUrl = HttpUrl
                .parse(String.format("http://localhost:%d/movies/search", port));
    }

    @After
    public void shutdownServer() {
        Server.getHttpserver().stop(0);
    }

    @Test
    public void shouldAnswerToGetRequest() throws IOException {
        String[] severalMovieNameCases = new String[]{"titanic", "Titanic", "TiTaNiC"};

        for (String testMovie : severalMovieNameCases) {
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

            int expected = response.code();
            int actual = 200;

            assertThat(expected).isEqualTo(actual);
        }
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

        assertThat(expected).isEqualTo(actual);
    }
}

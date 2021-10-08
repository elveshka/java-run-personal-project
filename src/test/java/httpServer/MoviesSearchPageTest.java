package httpServer;

import static org.assertj.core.api.Assertions.*;

import httpServer.Server.Server;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;

public class MoviesSearchPageTest {


    @Before
    public void generatePort() {
        Random random = new Random(1);
//        for (;;) {
//            try {
//                return new ServerSocket(port);
//            } catch (IOException ex) {
//                // try next port
//            }
//        }
        // if the program gets here, no port in the range was found
    }

    @Test
    public void shouldReturnTrue() {
        assertThat(true).isTrue();
    }
}

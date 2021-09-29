package httpServer.Resources;

import java.util.HashMap;
import java.util.Map;

public class Schedule {
    private final Map<Integer, Movie> sessions = new HashMap<>();
    private final Map<Integer, SessionTickets> tickets = new HashMap<>();

    public Schedule() {
        for (int i = 0; i < 24; ++i) {
            sessions.put(i, null);
        }
    }

    public void addSession(int time, Movie movie, int size) {
        if (time >= 0 && time <= 23) {
            if (sessions.get(time) == null) {
                sessions.put(time, movie);
                tickets.put(time, new SessionTickets(size));
            }
        }
    }

    public Map<Integer, Movie> getSessions() {
        return sessions;
    }

    public Map<Integer, SessionTickets> getTickets() {
        return tickets;
    }
}

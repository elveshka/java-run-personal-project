package httpServer.Resources;

import java.util.HashMap;
import java.util.Map;

public class Schedule {
    private final Map<Integer, Movie> sessions = new HashMap<>();
    private final Map<Integer, SessionTickets> tickets = new HashMap<>();

    private static final int HOURS_IN_DAY = 24;

    public Schedule() {
        for (int i = 0; i < HOURS_IN_DAY; ++i) {
            sessions.put(i, null);
        }
    }

    public void addSession(int time, Movie movie, int size) {
        if (sessions.containsKey(time)) {
            sessions.put(time, movie);
            tickets.put(time, new SessionTickets(size));
        }
    }

    public Map<Integer, Movie> getSessions() {
        return sessions;
    }

    public Map<Integer, SessionTickets> getTickets() {
        return tickets;
    }
}

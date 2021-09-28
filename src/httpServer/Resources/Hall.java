package httpServer.Resources;

public class Hall {

    private final String hallName;

    private final int hallSize;

    private Schedule schedule = new Schedule();

    public Hall(String hallName, int size) {
        this.hallName = hallName;
        this.hallSize = size;
    }

    public String getHallName() {
        return hallName;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public int getHallSize() {
        return hallSize;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}

package httpServer.Resources;

//this class creating schedule for all halls depends on date/time
//but not now :D
public class Session {
    private final String sessionDate; // should be Date type
    private final Schedule schedule = new Schedule();

    public Session(String sessionDate) {
        this.sessionDate = sessionDate;
        schedule.setSession(DataBase.getDb());
    }

    public String getSessionDate() {
        return sessionDate;
    }

    public Schedule getSchedule() {
        return schedule;
    }
}


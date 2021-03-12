package Response;

public class LoadResponse {
    private int userCount;
    private int personCount;
    private int eventCount;

    private boolean success;
    private String message;

    public LoadResponse(int userCount, int personCount, int eventCount, boolean success) {
        this.userCount = userCount;
        this.personCount = personCount;
        this.eventCount = eventCount;
        this.success = success;
        this.message = "Successfully added " +
                userCount + " users, " +
                personCount + " persons," +
                " and " + eventCount + " events to the database.";
    }

    public LoadResponse(String message, boolean success){
        this.message = "Error:" + message;
        this.success = success;
    }

    public int getUserCount() {
        return userCount;
    }

    public int getPersonCount() {
        return personCount;
    }

    public int getEventCount() {
        return eventCount;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}

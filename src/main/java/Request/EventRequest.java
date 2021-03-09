package Request;

public class EventRequest {

    private String authtoken;
    private String eventID;

    public EventRequest(String authtoken) {
        this.authtoken = authtoken;
    }

    public EventRequest(String authtoken, String eventID) {
        this.authtoken = authtoken;
        this.eventID = eventID;
    }

    public String getAuthtoken() {
        return this.authtoken;
    }

    public String getEventID() {
        return eventID;
    }
}

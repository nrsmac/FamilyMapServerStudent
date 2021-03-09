package Request;

public class EventRequest {

    private String authToken;
    private String eventID;

    public EventRequest(String authToken) {
        this.authToken = authToken;
    }

    public EventRequest(String authToken, String eventID) {
        this.authToken = authToken;
        this.eventID = eventID;
    }

    public String getAuthToken() {
        return this.authToken;
    }

    public String getEventID() {
        return eventID;
    }
}

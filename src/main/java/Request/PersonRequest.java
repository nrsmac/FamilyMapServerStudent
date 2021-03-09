package Request;

public class PersonRequest {
    private String authToken;
    private String personID;

    public PersonRequest(String authToken) {
        this.authToken = authToken;
    }

    public PersonRequest(String authToken, String personID) {
        this.authToken = authToken;
        this.personID = personID;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getPersonID() {
        return personID;
    }
}

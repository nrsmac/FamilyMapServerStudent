package Request;

public class PersonsRequest {
    private String authtoken;
    private String personID;

    public PersonsRequest(String authtoken) {
        this.authtoken = authtoken;
    }

    public PersonsRequest(String authtoken, String personID) {
        this.authtoken = authtoken;
        this.personID = personID;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public String getPersonID() {
        return personID;
    }
}

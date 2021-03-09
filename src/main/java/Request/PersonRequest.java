package Request;

public class PersonRequest {
    private String authtoken;
    private String personID;

    public PersonRequest(String authtoken) {
        this.authtoken = authtoken;
    }

    public PersonRequest(String authtoken, String personID) {
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

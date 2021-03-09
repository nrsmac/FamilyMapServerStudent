package Response;

/**
 * A register response sent from the service to the handler
 */
public class RegisterResponse implements IResponse {
    public RegisterResponse(String authtoken, String username, String personId, boolean success) {
        this.authtoken = authtoken;
        this.username = username;
        this.personId = personId;
        this.success = success;
    }

    public RegisterResponse(boolean success, String message){
        this.success = success;
        this.message = message;
    }

    private String authtoken;
    private String username;
    private String personId;
    private boolean success;

    private String message;

    public RegisterResponse() {

    }

    public String getAuthtoken() {
        return authtoken;
    }

    public String getUsername() {
        return username;
    }

    public String getPersonId() {
        return personId;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

}

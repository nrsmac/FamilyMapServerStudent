package Response;


/**
 * A login response sent from the service to the handler
 */
public class LoginResponse implements IResponse{
    private String authToken;
    private String username;
    private String personId;
    private final boolean success;
    private String message;

    public LoginResponse(String authToken, String username, String personId, boolean success){
        this.authToken = authToken;
        this.username = username;
        this.personId = personId;
        this.success = success;
    }


    /**
     * @param message
     * @param success
     */
    public LoginResponse(String message, boolean success) {
        this.success = success;
        this.message = message;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getUsername() {
        return username;
    }

    public String getPersonId() {
        return personId;
    }

    public boolean isSuccessful() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}

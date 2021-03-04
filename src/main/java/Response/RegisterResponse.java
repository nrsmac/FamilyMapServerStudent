package Response;

import Model.Model;

/**
 * A register response sent from the service to the handler
 */
public class RegisterResponse implements IResponse {
    public RegisterResponse(String authToken, String username, String personId, boolean success) {
        this.authToken = authToken;
        this.username = username;
        this.personId = personId;
        this.success = success;
    }

    public RegisterResponse(boolean success, String message){
        this.success = success;
        this.message = message;
    }

    private String authToken;
    private String username;
    private String personId;
    private boolean success;

    private String message;

    public RegisterResponse() {

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

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

}

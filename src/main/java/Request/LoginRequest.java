package Request;

import Model.Model;

/**
 * A login request sent from the handler to the service
 */
public class LoginRequest implements IRequest{

    private final String password;
    private final String username;

    /**
     * Creates a new request.
     */
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

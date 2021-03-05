package Model;

import java.util.ArrayList;

/**
 * Represents an authToken locally within the model.
 */
public class AuthToken implements IModelElement{
    /**
     *
     */
    private String username;
    /**
     *
     */
    private String auth_token;

    /**
     * Instantiates a new Auth token.
     *
     * @param username   the username
     * @param auth_token the auth token
     */
    public AuthToken(String username, String auth_token){
        this.username = username;
        this.auth_token = auth_token;
    }

    /**
     * Generate auth token string.
     *
     * @return an an authToken which has not already been used
     */
    public static String generateAuthToken(){
        //TODO: search usedAuthTokens with already used authTokens
        return "";
    }

    public String getAuthToken() {
        return this.auth_token;
    }
    public String getUsername() { return this.username; }
}

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
     *
     */
    private ArrayList<String> usedAuthTokens;

    /**
     * Instantiates a new Auth token.
     *
     * @param username   the username
     * @param auth_token the auth token
     * @param model      the model
     */
    public AuthToken(String username, String auth_token, Model model){
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

    /**
     * @return username associated with authToken
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Gets auth token.
     *
     * @param username the username
     * @return authToken associated with username
     */
    public String getAuthToken(String username) {
        return auth_token;
    }
}

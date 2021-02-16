package Model;

import java.util.ArrayList;

/**
 * Represents an authToken locally within the model.
 */
public class AuthToken implements IModelElement{
    private String username;
    private String authToken;
    private ArrayList<String> usedAuthTokens;

    public AuthToken(String username, String authToken, Model model){
        this.username = username;
        this.authToken = authToken;
    }

    /**
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
     * @return authToken associated with username
     */
    public String getAuthToken(String username) {
        return authToken;
    }
}

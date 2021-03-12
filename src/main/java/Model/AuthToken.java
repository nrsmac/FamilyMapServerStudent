package Model;

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
        return "";
    }

    public String getAuthToken() {
        return this.auth_token;
    }
    public String getAssociatedUsername() { return this.username; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthToken authToken = (AuthToken) o;

        if (username != null ? !username.equals(authToken.username) : authToken.username != null) return false;
        return auth_token != null ? auth_token.equals(authToken.auth_token) : authToken.auth_token == null;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (auth_token != null ? auth_token.hashCode() : 0);
        return result;
    }
}

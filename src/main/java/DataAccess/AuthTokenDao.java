package DataAccess;

import Model.Model;
import Model.AuthToken;

import java.util.ArrayList;

/**
 * Interacts with the AuthToken table in the database via JDBC.
 * Called by the Service classes
 */
public class AuthTokenDao implements IDao{

    /**
     * Instantiation of the model
     */
    private Model model;

    /**
     * Instantiation of the database.
     */
    private Database db;

    /**
     * List of all AuthTokens in the model
     */
    private ArrayList<AuthToken> auth_tokens;

    /**
     * Instantiates a new Auth token dao.
     *
     * @param model the reference model
     */
    public AuthTokenDao(Model model){

    }

    /**
     * Adds the authToken to the AuthToken member variable
     *
     * @param authToken the user to be added
     */
    public void add(AuthToken authToken){

    }

    public int hashCode(){
        return 0;
    }

    public String toString(){
        return "";
    }
}

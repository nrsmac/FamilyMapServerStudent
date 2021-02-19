package DataAccess;

import Model.Model;
import Model.User;

import java.util.ArrayList;

/**
 * Interacts with the user table in the database via JDBC.
 * Called by the Service classes.
 */
public class UserDao implements IDao{
    /**
     * Instantiation of the model
     */
    private Model model;

    /**
     * Instantiation of the database.
     */
    private Database db;

    /**
     * List of all users in the model
     */
    private ArrayList<User> users;

    public UserDao(Model model){

    }
    /**
     * Adds the user to the user member variable
     *
     * @param user the user to be added
     */
    public void add(User user){

    }

    public int hashCode(){
        return 0;
    }

    public String toString(){
        return "";
    }
}

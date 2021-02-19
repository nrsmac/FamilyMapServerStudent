package DataAccess;

import Model.Model;
import Model.Person;

import java.util.ArrayList;

/**
 * Interacts with the persons table in the database via JDBC.
 * Called by the Service classes
 */
public class PersonDao implements IDao{

    /**
     * Instantiation of the model
     */
    private Model model;

    /**
     * Instantiation of the database.
     */
    private Database db;

    /**
     * List of all persons in the model
     */
    private ArrayList<Person> persons;

    /**
     * Instantiates a new Person dao.
     *
     * @param model the model
     */
    public PersonDao(Model model){

    }

    /**
     * Adds the person to the persons member variable
     *
     * @param person the person to be added
     */
    public void add(Person person){

    }

    public int hashCode(){
        return 0;
    }

    public String toString(){
        return "";
    }
}

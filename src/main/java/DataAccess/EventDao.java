package DataAccess;

import Model.Model;
import Model.Event;

import java.util.ArrayList;

/**
 * Interacts with the event table in the database via JDBC.
 * Called by the Service classes
 */
public class EventDao implements IDao{
    /**
     * Instantiation of the model
     */
    private Model model;
    /**
     * Instantiation of the database.
     */
    private Database db;

    /**
     * List of all Events in the model
     */
    private ArrayList<Event> events;

    /**
     * Instantiates a new Event dao.
     *
     * @param model the model
     */
    public EventDao(Model model){

    }

    /**
     * Adds the event to the events member variable
     *
     * @param event the event to be added
     */
    public void add(Event event){

    }

    public int hashCode(){
        return 0;
    }

    public String toString(){
        return "";
    }
}

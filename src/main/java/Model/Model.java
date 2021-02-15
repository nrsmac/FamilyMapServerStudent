package Model;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Represents the database in the context of the Java web server.
 */
public class Model {
    private DataAccess access;
    private ArrayList<IModelElement> elements;
    private ArrayList<User> users;
    private ArrayList<Person> persons;
    private ArrayList<Event> events;
    /**
     * Creates a reflection of database contents accessible within the server.
     * @param access takes in a Model.DataAccess object to recreate the database structure within the project.
     */
    public Model(DataAccess access) {
        this.access = access;
        try {
            this.elements = access.doTransaction();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Transaction threw SQL Error");
        }
    }

    /**
     * Updates model via Model.DataAccess.
     */
    public void updateModel(){

    }

    /**
     * @return users in model
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * @return persons in model
     */
    public ArrayList<Person> getPersons() {
        return this.persons;
    }

    /**
     * @return events in model
     */
    public ArrayList<Event> getEvents() {
        return this.events;
    }
}

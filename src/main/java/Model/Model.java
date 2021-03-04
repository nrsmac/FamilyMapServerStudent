package Model;

import DataAccess.Database;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Represents the database in the context of the Java web server.
 */
public class Model {
    private Database access;
    private ArrayList<IModelElement> elements;
    private ArrayList<User> users;
    private ArrayList<Person> persons;
    private ArrayList<Event> events;
    /**
     * Creates a reflection of database contents accessible within the server.
     * @param access takes in a DataAccess.DataAccess object to recreate the database structure within the project.
     */
    public Model(Database access) {
        this.access = access;
        try {
            this.elements = access.doTransaction();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Transaction threw SQL Error");
        }
    }

    /**
     * Updates model via DataAccess.DataAccess.
     */
    public void updateModel(){

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

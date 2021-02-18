package DataAccess;

import Model.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Driver for all JDBC interactions with the DAO objects
 */
public class DataAccess {
    ArrayList<UserDao> users;
    ArrayList<PersonDao> persons;
    ArrayList<EventDao> events;

    public DataAccess(){

    }

    /**
     * Performs a database transaction which includes resetting all tables, resetting the auto-increment primary key sequences,
     * inserting Elements, people and events, updating all info, and retrieving the resulting information.
     * @return resulting model elements.
     * @throws SQLException if sql error occurs.
     */

    public ArrayList<IModelElement> doTransaction() throws SQLException {
        return new ArrayList<>();
    }

    /**
     * Generates a list of elements to be inserted into the database
     * @return the elements
     */
    private ArrayList<IModelElement> getElementsToInsert(){
        return new ArrayList<>();
    }

    public static void main(String[] args){

    }

    /**
     * Prints a list of elements by calling IModelElement.toString() on each element and pre-pending the resulting string with the specified prefix. 
     * The prefix is useful for indenting the printout. 
     * @param prefix the prefix inserted before each printout
     * @param elements the elements to be printed
     */
    private void printElements(String prefix, ArrayList<IModelElement> elements){

    }

    /**
     * Removes all elements form the database and resets the primary key sequence.
     * @param connection the connection to be used for this operation
     */
    private void removeElements(Connection connection){

    }

    /**
     * Updates the element with the ID of the specified element, setting all values to the current values of the specified element.
     * @param connection the connection to be used for this operation
     * @param element the element to be updated
     */
    private void updateElement(Connection connection, IModelElement element){

    }

    public int hashCode(){
        return 0;
    }

    public String toString(){
        return "";
    }
}

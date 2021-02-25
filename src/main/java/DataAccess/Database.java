package DataAccess;

import Model.*;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Represents the sql database. Called by the DAOs.
 */
public class Database {
    private Connection connection;


    /**
     * Called before every change to our database to open the connection.
     * Statements created by that connection are used to initiate transactions.
     * @return Connection
     */
    public Connection openConnection() throws DataAccessException {
        try {
            final String CONNECTION_URL = "jdbc:sqlite:database.db";
            connection = DriverManager.getConnection(CONNECTION_URL);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Unable to open connection to database.");
        }
        return connection;
    }

    public Connection getConnection() throws DataAccessException {
        if(connection== null){
            return openConnection();
        } else {
            return connection;
        }
    }

    /**
     * When we are done manipulating, we need to close the connection
     * End the connection and allow us to commit our changes or to rollback.
     * @param commit determines if we commit changes or rollback.
     * @throws DataAccessException on sql error
     */
    public void closeConnection(boolean commit) throws DataAccessException{
        try {
            if (commit) {
                //Commit changes
                connection.commit();
            } else {
                connection.rollback();
            }

            connection.close();
            connection = null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Unable to close database connection");
        }
    }

    /**
     * Clears all tables
     * @throws DataAccessException when sql error encountered
     */
    public void clearTables() throws DataAccessException{
        try (Statement stmt = connection.createStatement()){
            String sql = "" +
                    "DELETE FROM users; " +
                    "DELETE FROM events;" +
                    "DELETE FROM persons;" +
                    "DELETE FROM auth_tokens;";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("SQL error while clearing tables");
        }
    }

    /**
     * Performs a database transaction which includes resetting all tables, resetting the auto-increment primary key sequences,
     * inserting Elements, people and events, updating all info, and retrieving the resulting information.
     *
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

    /**
     * Main.
     *
     * @param args the args
     */
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

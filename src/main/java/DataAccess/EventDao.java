package DataAccess;

import Model.Model;
import Model.Event;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * Interacts with the event table in the database via JDBC.
 * Called by the Service classes
 */
public class EventDao implements IDao{
   private final Connection connection;

    public EventDao(Connection connection){
        this.connection= connection;
    }

    /**
     * Adds the event to the database
     *
     * @param event the event to be added
     */
    public void add(Event event){
        String sql = "insert into persons () VALUES()";
    }

    public int hashCode(){
        return 0;
    }

    public String toString(){
        return "";
    }
}

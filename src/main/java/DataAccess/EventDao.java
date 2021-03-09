package DataAccess;

import Model.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public void insertEvent(Event event) throws DataAccessException {
        String sql = "INSERT INTO events (eventID, associatedUsername, personID, eventType, latitude, longitude, country, city, year) VALUES(?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, event.getEventID());
            stmt.setString(2, event.getAssociatedUsername());
            stmt.setString(3,event.getPersonID());
            stmt.setString(4, event.getEventType());
            stmt.setDouble(5, event.getLatitude());
            stmt.setDouble(6, event.getLongitude());
            stmt.setString(7, event.getCountry());
            stmt.setString(8, event.getCity());
            stmt.setInt(9, event.getYear());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error inserting into events");
        }
    }

    /**
     * Find an event in database given id
     * @param eventID  to find
     * @return found event
     * @throws DataAccessException on sql exception
     */
    public Event findByEventId(String eventID) throws DataAccessException{
        Event event = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM events WHERE eventID =?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, eventID);
            rs = stmt.executeQuery();
            if (rs.next()){
                event = new Event(rs.getString("eventID"),
                        rs.getString("associatedUsername"),
                        rs.getString("personID"),
                        rs.getDouble("latitude"),
                        rs.getDouble("longitude"),
                        rs.getString("country"),
                        rs.getString("city"),
                        rs.getString("eventType"),
                        rs.getInt("year"));
                return event;
            }
            throw new DataAccessException("Requested event not found");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Issue retrieving event from database");
        } finally {
            if (rs != null){
                try{
                    rs.close();
                } catch (SQLException e){
                    e.printStackTrace();
                    throw new DataAccessException("Issue retrieving event from database");
                }
            }
        }
    }

    /**
     * Find an event in database given associatedUsername
     * @param associatedUsername  to find
     * @return found event
     * @throws DataAccessException on sql exception
     */
    public ArrayList<Event> findByUser(String associatedUsername) throws DataAccessException{
        ArrayList<Event> events = new ArrayList<Event>();
        ResultSet rs = null;
        String sql = "SELECT * FROM events WHERE associatedUsername =?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, associatedUsername);
            rs = stmt.executeQuery();
            if (!rs.next()) {
                throw new DataAccessException("No events associated with associatedUsername");
            }
            while (rs.next()){
                Event event;
                event = new Event(rs.getString("eventID"),
                        rs.getString("associatedUsername"),
                        rs.getString("personID"),
                        rs.getDouble("latitude"),
                        rs.getDouble("longitude"),
                        rs.getString("country"),
                        rs.getString("city"),
                        rs.getString("eventType"),
                        rs.getInt("year"));
                events.add(event);
            }
            return events;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Issue retrieving event from database");
        } finally {
            if (rs != null){
                try{
                    rs.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Find an event in database given PersonId
     * @param personId  to find
     * @return found event
     * @throws DataAccessException on sql exception
     */
    public ArrayList<Event> findByPersonId(String personId) throws DataAccessException{
        ArrayList<Event> events = new ArrayList<Event>();
        ResultSet rs = null;
        String sql = "SELECT * FROM events WHERE personID =?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if (!rs.next()) {
                throw new DataAccessException("No events associated with person id");
            }
            while (rs.next()){
                Event event;
                event = new Event(rs.getString("eventID"),
                        rs.getString("associatedUsername"),
                        rs.getString("personID"),
                        rs.getDouble("eventType"),
                        rs.getDouble("latitude"),
                        rs.getString("longitude"),
                        rs.getString("country"),
                        rs.getString("city"),
                        rs.getInt("year"));
                events.add(event);
            }
            return events;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Issue retrieving event from database");
        } finally {
            if (rs != null){
                try{
                    rs.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Clears all events from database
     */
    public void clearEvents() throws DataAccessException {
        String sql = "DELETE FROM events";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            throw new DataAccessException("Issue clearing events");
        }
    }

    /**
     * get event count in database
     * @return number of events in database
     * @throws DataAccessException on sql error
     */
    public int getEventCount() throws DataAccessException {
        int eventCount = 0;
        ResultSet rs = null;
        String sql = "SELECT * FROM events";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            rs = stmt.executeQuery();
            while (rs.next()){
                eventCount++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException();
        }
        return eventCount;
    }
}

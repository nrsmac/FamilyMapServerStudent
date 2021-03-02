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
        String sql = "INSERT INTO events (event_id, username, person_id, event_type, latitude, longitude, country, city, year) VALUES(?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, event.getEvent_id());
            stmt.setString(2, event.getUsername());
            stmt.setString(3,event.getPerson_id());
            stmt.setString(4, event.getEvent_type());
            stmt.setDouble(5, event.getLatitude());
            stmt.setDouble(6, event.getLongitude());
            stmt.setString(7, event.getCountry());
            stmt.setString(8, event.getCity());
            stmt.setString(9, event.getYear());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error inserting into events");
        }
    }

    /**
     * Find an event in database given id
     * @param event_id  to find
     * @return found event
     * @throws DataAccessException on sql exception
     */
    public Event findByEventId(String event_id) throws DataAccessException{
        Event event = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM events WHERE event_id =?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, event_id);
            rs = stmt.executeQuery();
            if (rs.next()){
                event = new Event(rs.getString("event_id"),
                        rs.getString("username"),
                        rs.getString("person_id"),
                        rs.getDouble("event_type"),
                        rs.getDouble("latitude"),
                        rs.getString("longitude"),
                        rs.getString("country"),
                        rs.getString("city"),
                        rs.getString("year"));
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
     * Find an event in database given username
     * @param username  to find
     * @return found event
     * @throws DataAccessException on sql exception
     */
    public ArrayList<Event> findByUser(String username) throws DataAccessException{
        ArrayList<Event> events = new ArrayList<Event>();
        ResultSet rs = null;
        String sql = "SELECT * FROM events WHERE username =?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if (!rs.next()) {
                throw new DataAccessException("No events associated with username");
            }
            while (rs.next()){
                Event event;
                event = new Event(rs.getString("event_id"),
                        rs.getString("username"),
                        rs.getString("person_id"),
                        rs.getDouble("event_type"),
                        rs.getDouble("latitude"),
                        rs.getString("longitude"),
                        rs.getString("country"),
                        rs.getString("city"),
                        rs.getString("year"));
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
        String sql = "SELECT * FROM events WHERE person_id =?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if (!rs.next()) {
                throw new DataAccessException("No events associated with person id");
            }
            while (rs.next()){
                Event event;
                event = new Event(rs.getString("event_id"),
                        rs.getString("username"),
                        rs.getString("person_id"),
                        rs.getDouble("event_type"),
                        rs.getDouble("latitude"),
                        rs.getString("longitude"),
                        rs.getString("country"),
                        rs.getString("city"),
                        rs.getString("year"));
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

package DataAccess;

import Model.Model;
import Model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Interacts with the user table in the database via JDBC.
 * Called by the Service classes.
 */
public class PersonDao implements IDao{
    private final Connection connection;

    public PersonDao(Connection connection){
        this.connection = connection;
    }

    /**
     * Adds the person to the database
     *
     * @param person the person to be added
     */
    public void insertPerson(Person person) throws DataAccessException {
        String sql = "INSERT INTO persons (person_id, username, first_name, last_name, gender, father_id, mother_id, spouse_id) VALUES(?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, person.getPersonId());
            stmt.setString(2, person.getUsername());
            stmt.setString(3, person.getFirstName());
            stmt.setString(4, person.getLastName());
            stmt.setString(5, person.getGender());
            stmt.setString(6, person.getFatherId());
            stmt.setString(7, person.getMotherId());
            stmt.setString(8, person.getSpouseId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error while inserting into Users");
        }
    }

    /**
     * Find a person in database given id. If multiple (SHOULDN'T HAPPEN), returns top result
     * @param person_id of person to find
     * @return found person
     * @throws DataAccessException on sql exception
     */
    public Person find(String person_id) throws DataAccessException{
        Person person = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM persons WHERE person_id =?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, person_id);
            rs = stmt.executeQuery();
            if (rs.next()){
                person = new Person(rs.getString("person_id"),
                        rs.getString("username"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("gender"),
                        rs.getString("father_id"),
                        rs.getString("mother_id"),
                        rs.getString("spouse_id"));
                return person;
            }
            throw new DataAccessException();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException();
        } finally {
            if (rs!=null){

                try{
                    rs.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Clears all persons from database
     */
    public void clearPersons() throws DataAccessException{
        String sql = "DELETE FROM persons";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException();
        }
    }

    public int getPersonCount() throws DataAccessException {
        int rowCount = 0;
        ResultSet rs = null;
        String sql = "SELECT * FROM persons";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            rs = stmt.executeQuery();
            while (rs.next()) {
                rowCount++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException();
        }
        return rowCount;
    }

    public int hashCode(){
        return 0;
    }

    public String toString(){
        return "";
    }
}

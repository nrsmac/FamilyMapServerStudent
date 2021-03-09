package DataAccess;

import Model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Interacts with the user table in the database via JDBC.
 * Called by the Service classes.
 */
public class PersonDao implements IDao{
    /**
     * The connection to the database
     */
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
        String sql = "INSERT INTO persons (personID, associatedUsername, firstName, lastName, gender, fatherID, motherID, spouseID) VALUES(?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, person.getPersonId());
            stmt.setString(2, person.getAssociatedUsername());
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
        String sql = "SELECT * FROM persons WHERE personID =?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, person_id);
            rs = stmt.executeQuery();
            if (rs.next()){
                person = new Person(rs.getString("personID"),
                        rs.getString("associatedUsername"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("gender"),
                        rs.getString("fatherID"),
                        rs.getString("motherID"),
                        rs.getString("spouseID"));
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

    public ArrayList<Person> getPeople(String associatedUsername) throws DataAccessException {
        ArrayList<Person> people = new ArrayList<>();

        ResultSet rs = null;
        String sql = "SELECT * FROM persons WHERE associatedUsername = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, associatedUsername);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Person person = new Person(
                        rs.getString("personID"),
                        rs.getString("associatedUsername"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("gender"),
                        rs.getString("fatherID"),
                        rs.getString("motherID"),
                        rs.getString("spouseID")
                );
                people.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException();
        }

        return people;
    }
}

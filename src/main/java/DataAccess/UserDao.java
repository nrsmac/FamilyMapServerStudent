package DataAccess;

import Model.Model;
import Model.User;

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
public class UserDao implements IDao{
    private final Connection connection;

    public UserDao(Connection connection){
        this.connection = connection;
    }

    /**
     * Adds the user to the database
     *
     * @param user the user to be added
     */
    public void insertUser(User user) throws DataAccessException {
        //TODO implement
        String sql = "INSERT INTO users (user_id, username, password, email, first_name, last_name, gender) VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getUser_id());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getFirst_name());
            stmt.setString(6, user.getLast_name());
            stmt.setString(7, user.getGender());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error while inserting into Users");
        }
    }

    /**
     * Get all users in database
     * @return all users in database
     */
    public TreeSet<User> retrieveUsers(){
        TreeSet<User> users = new TreeSet<User>();

        //TODO implement. call find

        return users;
    }

    /**
     * Find a user in database given id. If multiple (SHOULDN'T HAPPEN), returns top result
     * @param user_id of user to find
     * @return found user
     * @throws DataAccessException on sql exception
     */
    public User find(String user_id) throws DataAccessException{
        User user = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM users WHERE user_id =?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, user_id);
            rs = stmt.executeQuery();
            if (rs.next()){
                user = new User(rs.getString("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("gender"));
                return user;
            }
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
        return null;
    }

    /**
     * Clears all users from database
     */
    public void clearUsers() throws DataAccessException{
        String sql = "TRUNCATE TABLE user";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException();
        }
    }

    public int hashCode(){
        return 0;
    }

    public String toString(){
        return "";
    }
}

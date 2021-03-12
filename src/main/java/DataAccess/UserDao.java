package DataAccess;

import Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        String sql = "INSERT INTO users (personID, username, password, email, firstName, lastName, gender) VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getPersonID());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getFirstName());
            stmt.setString(6, user.getLastName());
            stmt.setString(7, user.getGender());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error while inserting into Users: " + e.getMessage());
        }
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
        String sql = "SELECT * FROM users WHERE personID =?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, user_id);
            rs = stmt.executeQuery();
            if (rs.next()){
                user = new User(rs.getString("personID"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("gender"));
                return user;
            }
            throw new DataAccessException("User not found");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Issue retrieving user from database" + e.getMessage());
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
     * Clears all users from database
     */
    public void clearUsers() throws DataAccessException{
        String sql = "DELETE FROM users";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException();
        }
    }

    public int getUserCount() throws DataAccessException {
        int rowCount = 0;
        ResultSet rs = null;
        String sql = "SELECT * FROM users";
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

    public boolean containsUser(User user) throws DataAccessException {
        boolean contains = false;
        ResultSet rs = null;
        String sql = "SELECT * FROM users";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
                rs = stmt.executeQuery();
                while (rs.next()){
                    User daoUser = new User(rs.getString("personID"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("email"),
                            rs.getString("firstName"),
                            rs.getString("lastName"),
                            rs.getString("gender"));
                    if(daoUser.equals(user)){
                        contains = true;
                        return contains;
                    }
                }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException();
        }
        return contains;
    }

    public String getIdByUsername(String username) throws DataAccessException {
        ResultSet rs = null;
        String sql = "SELECT * FROM users";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            rs = stmt.executeQuery();
            while (rs.next()){
                User daoUser = new User(rs.getString("personID"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("gender"));
                if(daoUser.getUsername().equalsIgnoreCase(username)){
                    return daoUser.getPersonID();
                }
            }
            throw new DataAccessException("Username not found");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException();
        }
    }
}

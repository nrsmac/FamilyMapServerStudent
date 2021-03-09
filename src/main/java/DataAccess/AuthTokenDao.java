package DataAccess;

import Model.AuthToken;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Interacts with the AuthToken table in the database via JDBC.
 * Called by the Service classes
 */
public class AuthTokenDao implements IDao{
    private final Connection connection;

    public AuthTokenDao(Connection connection){
        this.connection = connection;
    }

    /**
     * Adds the authToken to the AuthToken member variable
     *
     * @param authToken the auth token to be added
     */
    public void add(AuthToken authToken) throws DataAccessException {
        String sql = "INSERT INTO auth_tokens (username, auth_token) VALUES(?,?)";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, authToken.getAssociatedUsername());
            stmt.setString(2, authToken.getAuthToken());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("error while inserting authtoken");
        }
    }

    public AuthToken findByUsername(String username) throws DataAccessException {
        AuthToken authToken = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM auth_tokens WHERE username =?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, username);
            rs=stmt.executeQuery();
            if(rs.next()){
                authToken = new AuthToken(rs.getString("username"),
                        rs.getString("auth_token"));
                return authToken;
            }
            throw new DataAccessException();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Issue retrieving authtoken from database");
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

    public AuthToken findByAuthtoken(String authKey) throws DataAccessException {
        AuthToken authToken = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM auth_tokens WHERE auth_token =?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, authKey);
            rs=stmt.executeQuery();
            if(rs.next()){
                authToken = new AuthToken(rs.getString("username"),
                        rs.getString("auth_token"));
                return authToken;
            }
            throw new DataAccessException();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Issue retrieving user by authtoken from database");
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

    public int hashCode(){
        return 0;
    }

    public String toString(){
        return "";
    }

    public void clearTokens() throws DataAccessException {
        String sql = "DELETE FROM auth_tokens";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException();
        }
    }
}

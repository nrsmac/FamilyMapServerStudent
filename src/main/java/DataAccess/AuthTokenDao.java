package DataAccess;

import Model.Model;
import Model.AuthToken;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

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
            stmt.setString(1, authToken.getAuthToken());
            stmt.setString(2, authToken.getAuthToken());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("error while inserting authtoken");
        }
    }

    public AuthToken find(String username) throws DataAccessException {
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

    public int hashCode(){
        return 0;
    }

    public String toString(){
        return "";
    }
}

package Services;

import DataAccess.*;
import Request.LoginRequest;
import Response.LoginResponse;

/**
 * Works with the Model and the DAOs to process requests
 */
public class LoginService {
    private Database db;
    /**
     * The response back to the handler
     */
    private LoginResponse response;


    public LoginService(LoginRequest request) throws DataAccessException {

        this.response = null;
        Database db = new Database();
        try{
            UserDao userDao = new UserDao(db.getConnection());
            AuthTokenDao authDao = new AuthTokenDao(db.getConnection());
            String userId = userDao.getIdByUsername(request.getUsername());
            String authToken = authDao.findByUsername(request.getUsername()).getAuthToken();
            String daoPassword = userDao.find(userId).getPassword();
            if(request.getPassword().equals(daoPassword)) {
                this.response = new LoginResponse(authToken,request.getUsername(),userId,true);
            } else {
                this.response = new LoginResponse("Error: Invalid password", false);
            }
            db.closeConnection(true);
        } catch (DataAccessException e) {
            this.response = new LoginResponse("Error: User not found", false);
            e.printStackTrace();
            db.closeConnection(false);
        }
    }

    public LoginResponse getResponse() {
        return this.response;
    }
}

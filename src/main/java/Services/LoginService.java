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


    public LoginService(LoginRequest request){

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
                this.response = new LoginResponse("Invalid password", false);
            }
        } catch (DataAccessException e) {
            this.response = new LoginResponse("User not found", false);
            e.printStackTrace();
        }
    }

    public LoginResponse getResponse() {
        return this.response;
    }
}

package Services;

import DataAccess.*;
import Model.Model;
import Model.User;
import Request.RegisterRequest;
import Response.RegisterResponse;

/**
 * Processes registration via DAOs as directed by requests, and passes responses to the register handler
 */
public class RegisterService {
    private Database db;
    /**
     * The request from the handler
     */
    private RegisterRequest request;
    /**
     * The response back to the handler
     */
    private RegisterResponse response;
    /**
     * The model that will be interacted with. Populated in the constructor by the model in the request.
     */
    private Model model;
    /**
     * Person DAO
     */
    private PersonDao personDao;
    /**
     * User DAO
     */
    private UserDao userDao;
    /**
     * Event DAO
     */
    private EventDao eventDao;

    /**
     * Instantiates a new Register service.
     *
     * @param request the request
     */
    public RegisterService(RegisterRequest request) {
        this.request = request;
        this.response = null;
        db = new Database();
        boolean success = false;
        try {
            userDao = new UserDao(db.getConnection());
            String userId = userDao.generateUserId();
            User user = new User(userId,
                    request.getUsername(),
                    request.getPassword(),
                    request.getEmail(),
                    request.getFirstName(),
                    request.getLastName(),
                    request.getGender());
            if(!userDao.containsUser(user)){
                userDao.insertUser(user);
                this.response = new RegisterResponse("authtoken goes here", user.getUsername(), userId, true);
            } else {
                this.response = new RegisterResponse(false, "User already exists"); //user already exists
            }
            db.closeConnection(true);
            //TODO handle incorrect user values
        } catch (DataAccessException e) {
            //Send other error response;
            this.response = new RegisterResponse(false, "Error adding user"); //user already exists
        }

    }

    public RegisterResponse getResponse() {
        return this.response;
    }

}

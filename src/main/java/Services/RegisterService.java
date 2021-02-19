package Services;

import DataAccess.EventDao;
import DataAccess.PersonDao;
import DataAccess.UserDao;
import Model.Model;
import Request.RegisterRequest;
import Request.RegisterRequest;
import Response.RegisterResponse;
import Response.RegisterResponse;

/**
 * Processes registration via DAOs as directed by requests, and passes responses to the register handler
 */
public class RegisterService {
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
    public RegisterService(RegisterRequest request){
        this.request = request;
    }

}

package Services;

import DataAccess.EventDao;
import DataAccess.PersonDao;
import DataAccess.UserDao;
import Model.Model;
import Request.LoginRequest;
import Response.LoginResponse;

/**
 * Works with the Model and the DAOs to process requests
 */
public class LoginService {
    /**
     * The request from the handler
     */
    private LoginRequest request;
    /**
     * The response back to the handler
     */
    private LoginResponse response;
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
     * Instantiates a new Login service.
     *
     * @param request the request
     */
    public LoginService(LoginRequest request){
        this.request = request;
    }

}

package Services;

import DataAccess.Database;
import DataAccess.EventDao;
import DataAccess.PersonDao;
import DataAccess.UserDao;
import Model.Model;
import Request.ClearRequest;
import Response.ClearResponse;

/**
 * Interacts with the model and DAOs to clear the database.
 */
public class ClearService {
    /**
     * The request from the handler
     */
    private ClearRequest request;
    /**
     * The response back to the handler
     */
    private ClearResponse response;
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
     * Instantiates a new Clear service.
     *
     * @param request the request
     */
    public ClearService(ClearRequest request){

    }
}

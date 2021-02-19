package Services;

import DataAccess.EventDao;
import DataAccess.PersonDao;
import DataAccess.UserDao;
import Model.Model;
import Request.FillRequest;
import Request.FillRequest;
import Response.FillResponse;
import Response.FillResponse;

/**
 * Interacts with the model and DAOs to fill the database.
 */
public class FillService {
    /**
     * The request from the handler
     */
    private FillRequest request;
    /**
     * The response back to the handler
     */
    private FillResponse response;
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
     * Instantiates a new Fill service.
     *
     * @param request the request
     */
    public FillService(FillRequest request){
       this.request = request;
    }
}

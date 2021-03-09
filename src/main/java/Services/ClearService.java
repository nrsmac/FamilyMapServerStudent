package Services;

import DataAccess.*;
import Model.Model;
import Request.ClearRequest;
import Response.ClearResponse;
import Response.RegisterResponse;

/**
 * Interacts with the model and DAOs to clear the database.
 */
public class ClearService {
    private Database db;
    private ClearResponse response;


    /**
     * Instantiates a new Clear service.
     *
     * @param request the request
     */
    public ClearService(ClearRequest request){
        this.response = null;
        db = new Database();
        try {
            UserDao userDao = new UserDao(db.getConnection());
            PersonDao personDao = new PersonDao(db.getConnection());
            EventDao eventDao = new EventDao(db.getConnection());
            AuthTokenDao authDao = new AuthTokenDao(db.getConnection());
            userDao.clearUsers();
            personDao.clearPersons();
            eventDao.clearEvents();
            authDao.clearTokens();
            this.response = new ClearResponse("Clear succeeded", true);
            db.closeConnection(true);
        } catch (DataAccessException e) {
            this.response = new ClearResponse("Error:" + e.toString(), false);
            e.printStackTrace();
        }
    }

    public ClearResponse getResponse() {
        return this.response;
    }
}

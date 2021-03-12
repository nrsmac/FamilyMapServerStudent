package Services;

import DataAccess.*;
import GenerateData.GeneratePeople;
import Model.Event;
import Model.Person;
import Model.User;
import Request.FillRequest;
import Response.FillResponse;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Interacts with the model and DAOs to fill the database.
 */
public class FillService {
    private int generations;
    private String username;
    private FillResponse response;
    private Database db = new Database();
    /**
     * Person DAO
     */
    private PersonDao personDao =new PersonDao(db.getConnection());
    /**
     * User DAO
     */
    private UserDao userDao = new UserDao(db.getConnection());
    /**
     * Event DAO
     */
    private EventDao eventDao = new EventDao(db.getConnection());


    /**
     * Instantiates a new Fill service.
     *
     * @param request the request
     */
    public FillService(FillRequest request) throws DataAccessException {
        this.generations = request.getGenerations(); //Default 4
        this.username = request.getUsername();

        try {
            personDao.clearPersonWithUsername(username);
            eventDao.clearEventsWithUsername(username);

            String userId = userDao.getIdByUsername(username);
            if(userId != null){
                User user = userDao.find(userId);
                Person userPerson = new Person(user.getPersonID(),
                        user.getUsername(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getGender());
                GeneratePeople generatePeople = new GeneratePeople();
                HashMap<String, HashSet<?>> data = generatePeople.generations(userPerson,this.generations); //TODO fix
                HashSet<Person> persons = (HashSet<Person>) data.get("persons");
                HashSet<Event> events = (HashSet<Event>) data.get("events");

                int personCount = 0;
                int eventCount = 0;
                for (Person p : persons){


                    try {
                        personDao.insertPerson(p);
                        personCount++;
                    } catch (DataAccessException e){
                        continue;
                    }
                }
                for (Event e : events){
                    eventDao.insertEvent(e);
                    eventCount++;
                }
                db.closeConnection(true);
                this.response = new FillResponse("Successfully added "+personCount+" persons and "+eventCount+" events to database.",true);
            } else {
                this.response = new FillResponse("FillServiceError: User doesnt exist",false);
                db.closeConnection(false);
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            this.response = new FillResponse("Fill error: " + e.getMessage(),false);
        }
    }

    public FillResponse getResponse() {
        return this.response;
    }
}

package Services;

import DataAccess.*;
import Model.AuthToken;
import Model.Person;
import Model.User;
import Model.Event;
import Request.LoadRequest;
import Response.LoadResponse;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.UUID;

public class LoadService {

    private LoadRequest request;
    private LoadResponse response;
    private Database db;

    public LoadService(LoadRequest request) throws DataAccessException {
        this.request = request;

        ArrayList<User> users;
        ArrayList<Person> persons;
        ArrayList<Event> events;

        users = request.getUsers();
        persons = request.getPersons();
        events = request.getEvents();

        pushToDaos(users,persons,events);
    }

    private void pushToDaos(ArrayList<User> users, ArrayList<Person> persons, ArrayList<Event> events) throws DataAccessException {
        db = new Database();
        try {
            Connection conn = db.getConnection();
            UserDao uDao = new UserDao(conn);
            PersonDao pDao = new PersonDao(conn);
            EventDao eDao = new EventDao(conn);
            AuthTokenDao aDao = new AuthTokenDao(conn);

            //Clear the daos
            uDao.clearUsers();
            pDao.clearPersons();
            eDao.clearEvents();
            aDao.clearTokens();

            int uCount = 0;
            for (User user : users){
                uDao.insertUser(user);
                AuthToken token = new AuthToken(user.getUsername(),UUID.randomUUID().toString());
                aDao.add(token);
                uCount++;
            }
            int pCount = 0;
            for (Person person : persons){
                pDao.insertPerson(person);
                pCount++;
            }
            int eCount = 0;
            for (Event event : events){
                eDao.insertEvent(event);
                eCount++;
            }

            db.closeConnection(true);
            this.response = new LoadResponse(uCount,pCount,eCount,true);
        } catch (DataAccessException e) {
            e.printStackTrace();
            this.response = new LoadResponse("Error: FAILED to load JSON into database. "+e.getMessage(), false);
            db.closeConnection(false);
        }
    }

    public LoadResponse getResponse() {
        return response;
    }
}

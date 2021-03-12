package Services;

import DataAccess.*;
import GenerateData.GeneratePeople;
import Model.AuthToken;
import Model.Event;
import Model.Person;
import Model.User;
import Request.RegisterRequest;
import Response.RegisterResponse;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

/**
 * Processes registration via DAOs as directed by requests, and passes responses to the register handler
 */
public class RegisterService {
    /**
     * The response back to the handler
     */
    private RegisterResponse response;

    /**
     * Instantiates a new Register service.
     *
     * @param request the request
     */
    public RegisterService(RegisterRequest request) throws DataAccessException {
        this.response = null;
        Database db = new Database();
        try {
            UserDao userDao = new UserDao(db.getConnection());
            PersonDao personDao = new PersonDao(db.getConnection());
            EventDao eventDao = new EventDao(db.getConnection());

            AuthTokenDao authDao = new AuthTokenDao(db.getConnection());

            String userId = UUID.randomUUID().toString().substring(0,6);
            User user = new User(userId,
                    request.getUsername(),
                    request.getPassword(),
                    request.getEmail(),
                    request.getFirstName(),
                    request.getLastName(),
                    request.getGender());

            if(!userDao.containsUser(user)){
                AuthToken token = new AuthToken(request.getUsername(),UUID.randomUUID().toString());
                userDao.insertUser(user);
                authDao.add(token);

                Person self = new Person(userId,user.getUsername(),user.getFirstName(),user.getLastName(),user.getGender());
                personDao.insertPerson(self);

                GeneratePeople generatePeople = new GeneratePeople();
                HashMap<String, HashSet<?>> data = generatePeople.generations(self,4); //4 generations of people
                HashSet<Person> persons = (HashSet<Person>) data.get("persons");
                HashSet<Event> events = (HashSet<Event>) data.get("events");

                for (Person p : persons){
                    try {
                        personDao.insertPerson(p);
                    } catch (DataAccessException e){
                        continue;
                    }
                }
                for (Event e : events){
                    eventDao.insertEvent(e);
                }

                db.closeConnection(true);
                this.response = new RegisterResponse(token.getAuthToken(), user.getUsername(), self.getPersonID(), true);
            } else {
                db.closeConnection(false);
                this.response = new RegisterResponse(false, "Error: User already exists"); //user already exists
            }

        } catch (DataAccessException e) {
            //Send other error response;
            this.response = new RegisterResponse(false, "Error: SQL Error" + e.getMessage()); //user already exists
            db.closeConnection(false);
        }

    }

    public RegisterResponse getResponse() {
        return this.response;
    }

}

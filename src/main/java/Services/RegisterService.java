package Services;

import DataAccess.*;
import GenerateData.GeneratePeople;
import Model.User;
import Model.Person;

import Model.AuthToken;
import Request.RegisterRequest;
import Response.RegisterResponse;

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
    public RegisterService(RegisterRequest request) {
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

                GeneratePeople generatePeople = new GeneratePeople(4, user.getUsername());
                for (Person p : generatePeople.getPersons()){
                    personDao.insertPerson(p);
                }

                this.response = new RegisterResponse(token.getAuthToken(), user.getUsername(), self.getPersonId(), true);
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

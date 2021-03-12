package Services;

import DataAccess.*;
import Model.AuthToken;
import Model.Person;
import Request.PersonsRequest;
import Response.PersonsResponse;

import java.util.ArrayList;

public class PersonsService {
    private PersonsResponse response;

    public PersonsService(PersonsRequest request) throws DataAccessException {
        this.response = null;
        Database db = new Database();
        try{
            UserDao userDao = new UserDao(db.getConnection());
            PersonDao personDao = new PersonDao(db.getConnection());
            EventDao eventDao = new EventDao(db.getConnection());
            AuthTokenDao authDao = new AuthTokenDao(db.getConnection());

            String authKey = request.getAuthtoken();

            try {
                AuthToken authToken = authDao.findByAuthtoken(authKey);
                String username = authToken.getAssociatedUsername();

                if (request.getPersonID() == null) {
                    ArrayList<Person> people = new ArrayList<>();

                    people = personDao.getPeople(username);
                    if(people != null){
                        this.response = new PersonsResponse(people,true);
                    } else {
                        this.response = new PersonsResponse(false, "Error: No people with token");
                    }
                } else {
                    Person person = personDao.find(request.getPersonID());
                    if(person != null && person.getAssociatedUsername().equals(username)){
                        this.response = new PersonsResponse(person.getAssociatedUsername(),
                                person.getPersonID(),
                                person.getFirstName(),
                                person.getLastName(),
                                person.getGender(),
                                person.getFatherID(),
                                person.getMotherID(),
                                person.getSpouseID(),
                                true);
                    } else {
                        this.response = new PersonsResponse(false, "Error: No person with associated with username");
                    }
                }
                db.closeConnection(true);

            } catch (DataAccessException e) {
                System.out.println("Person not found");
                e.printStackTrace();
                this.response = new PersonsResponse(false, "Error:  getting people");
                db.closeConnection(false);
            }

        } catch (DataAccessException e) {
            System.out.println("Error opening dao");
            this.response = new PersonsResponse(false, "Error: getting people");
            e.printStackTrace();
            db.closeConnection(false);
        }
    }

    public PersonsResponse getResponse() {
        return this.response;
    }
}

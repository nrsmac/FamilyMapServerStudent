package Services;

import DataAccess.*;
import Model.AuthToken;
import Model.Person;
import Request.PersonRequest;
import Response.PersonResponse;

import java.util.ArrayList;

public class PersonService {
    private PersonResponse response;

    public PersonService(PersonRequest request){
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
                        this.response = new PersonResponse(people,true);
                    } else {
                        this.response = new PersonResponse(false, "No people with token");
                    }
                } else {
                    Person person = personDao.find(request.getPersonID());
                    if(person != null && person.getAssociatedUsername().equals(username)){
                        this.response = new PersonResponse(person.getAssociatedUsername(),
                                person.getPersonId(),
                                person.getFirstName(),
                                person.getLastName(),
                                person.getGender(),
                                person.getFatherId(),
                                person.getMotherId(),
                                person.getSpouseId(),
                                true);
                    } else {
                        this.response = new PersonResponse(false, "No person with associated with username");
                    }
                }

            } catch (DataAccessException e) {
                System.out.println("Invalid authtoken");
                e.printStackTrace();
                this.response = new PersonResponse(false, "Error getting people");

            }
            db.closeConnection(true);

        } catch (DataAccessException e) {
            System.out.println("Error opening dao");
            this.response = new PersonResponse(false, "Error getting people");
            e.printStackTrace();
        }
    }

    public PersonResponse getResponse() {
        return this.response;
    }
}

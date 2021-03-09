package Response;

import Model.Person;

import java.util.ArrayList;

public class PersonResponse {
    private String message;
    private ArrayList<Person> people;
    private boolean success;
    private String associatedUsername;
    private String personId;
    private String firstName;
    private String lastName;
    private String gender;
    private String fatherID;
    private String motherID;
    private String spouseID;

    public PersonResponse(ArrayList<Person> people, boolean success) {
        this.people = people;
        this.success = success;
    }

    public PersonResponse(String associatedUsername,
                          String personId,
                          String firstName,
                          String lastName,
                          String gender,
                          String fatherID,
                          String motherID,
                          String spouseID,
                          boolean success) {
        this.associatedUsername = associatedUsername;
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseID = spouseID;
        this.success = success;
    }

    public PersonResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public String getPersonId() {
        return personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getFatherID() {
        return fatherID;
    }

    public String getMotherID() {
        return motherID;
    }

    public String getSpouseID() {
        return spouseID;
    }
}

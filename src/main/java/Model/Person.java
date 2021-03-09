package Model;

public class Person implements IModelElement{
    /**
     * person id
     */
    private String personID;
    /**
     * username associated with person
     */
    private String associatedUsername;
    /**
     * First name of person.
     */
    private String firstName;
    /**
     * Last name of person.
     */
    private String lastName;
    /**
     * ID of the father
     */
    private String fatherID;
    /**
     * ID Of the mother
     */
    private String motherID;
    /**
     * ID of the spouse
     */
    private String spouseID;
    /**
     * "m" if male, "f" if female
     */
    private String gender;

    public Person(String personID,
                  String associatedUsername,
                  String firstName,
                  String lastName,
                  String gender,
                  String fatherID,
                  String motherID,
                  String spouseID) {
        this.personID = personID;
        this.associatedUsername = associatedUsername;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseID = spouseID;
    }

    public Person(String personID, String associatedUsername, String firstName, String lastName, String gender) {
        this.personID = personID;
        this.associatedUsername = associatedUsername;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = null;
        this.motherID = null;
        this.spouseID = null;
    }

    /**
     * @return the unique id associated with this person of a user's pedigree (not null)
     */
    public String getPersonId() {
        return personID;
    }

    /**
     * @return the first name of this person (not null)
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the last name of this person (not null)
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return the id of this persons father (can be null)
     */
    public String getFatherId() {
        return fatherID;
    }

    /**
     * @return the id of this persons father (can be null)
     */
    public String getMotherId() {
        return motherID;
    }

    /**
     * @return id of this persons spouse (can be null)
     */
    public String getSpouseId() {
        return spouseID;
    }

    /**
     * @return the associated username with the element.
     */
    public String getAssociatedUsername() {
        return this.associatedUsername;
    }

    public String getGender() {
        return this.gender;
    }

    @Override
    public boolean equals(Object o){
        //TODO maybe more intense here
        if (o==null){
            return false;
        }
        if (o instanceof Person){
            Person oPerson = (Person) o;
            return oPerson.getPersonId().equals(getPersonId());
        } else {
            return false;
        }
    }

    public void setSpouseID(String spouseId){
        this.spouseID = spouseId;
    }

    public void setFatherID(String fatherId){
        this.fatherID = fatherId;
    }

    public void setMotherID(String motherId){
        this.motherID = motherId;
    }

    public String getPersonID(){
        return this.personID;
    }

}

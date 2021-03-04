package Model;

public class Person implements IModelElement{
    /**
     * person id
     */
    private String person_id;
    /**
     * username associated with person
     */
    private String username;
    /**
     * First name of person.
     */
    private String first_name;
    /**
     * Last name of person.
     */
    private String last_name;
    /**
     * ID of the father
     */
    private String father_id;
    /**
     * ID Of the mother
     */
    private String mother_id;
    /**
     * ID of the spouse
     */
    private String spouse_id;
    /**
     * "m" if male, "f" if female
     */
    private String gender;

    public Person(String person_id,
                  String username,
                  String first_name,
                  String last_name,
                  String gender,
                  String father_id,
                  String mother_id,
                  String spouse_id) {
        this.person_id = person_id;
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.father_id = father_id;
        this.mother_id = mother_id;
        this.spouse_id = spouse_id;
    }

    /**
     * @return the unique id associated with this person of a user's pedigree (not null)
     */
    public String getPersonId() {
        return person_id;
    }

    /**
     * @return the first name of this person (not null)
     */
    public String getFirstName() {
        return first_name;
    }

    /**
     * @return the last name of this person (not null)
     */
    public String getLastName() {
        return last_name;
    }

    /**
     * @return the id of this persons father (can be null)
     */
    public String getFatherId() {
        return father_id;
    }

    /**
     * @return the id of this persons father (can be null)
     */
    public String getMotherId() {
        return mother_id;
    }

    /**
     * @return id of this persons spouse (can be null)
     */
    public String getSpouseId() {
        return spouse_id;
    }

    /**
     * @return the associated username with the element.
     */
    public String getUsername() {
        return this.username;
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

}

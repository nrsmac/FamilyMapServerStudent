package Model;

/**
 * Represents a user, an instance of the IModelElement interface.
 * Stores user data: username, password, email, first and last name, gender, and a unique userId.
 */
public class User implements IModelElement{
    /**
     * Username of user
     */
    private String username;
    /**
     * password of user
     */
    private String password;
    /**
     * Email of user
     */
    private String email;
    /**
     * first name of user
     */
    private String firstName;
    /**
     * last name of user
     */
    private String lastName;
    /**
     * Gender of user "m" if male, "f" if female
     */
    private String gender;
    /**
     * ID of user
     */
    private String personID;

    public User(String personID,
                String username,
                String password,
                String email,
                String firstName,
                String lastName,
                String gender
                ) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.personID = personID;
    }
    /**
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the first name of the user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the last name of the user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return the gender of the user as a string "m" or "f"
     */
    public String getGender() {
        return gender;
    }

    /**
     * @return the unique user id of the user which relates to Person and Event objects
     */
    public String getPersonId() {
        return personID;
    }

    /**
     * @return the associated username with the element.
     */
    public String getUsername() {
        return this.username;
    }

    public int hashCode(){
        return 0;
    }


    public String toString(){
        return "";
    }

    @Override
    public boolean equals(Object o){
        if (o==null){
            return false;
        }
        if (o instanceof User){
            User oUser = (User) o;
            return
                    oUser.getUsername().equals(getUsername()) &&
                    oUser.getEmail().equals(getEmail()) &&
                    oUser.getFirstName().equals(getFirstName())&&
                    oUser.getLastName().equals(getLastName())&&
                    oUser.getGender().equals(getGender());
        } else {
            return false;
        }
    }
}

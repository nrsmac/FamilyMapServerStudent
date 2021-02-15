package Model;

/**
 * Represents a user, an instance of the IModelElement interface.
 * Stores user data: username, password, email, first and last name, gender, and a unique userId.
 */
public class User implements IModelElement{
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String userId;

    public User(String username,
                String password,
                String email,
                String firstName,
                String lastName,
                String gender,
                String userId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.userId = userId;
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
    public String getUserId() {
        return userId;
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
}

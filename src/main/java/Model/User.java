package Model;

/**
 * Represents a user, an instance of the IModelElement interface.
 * Stores user data: username, password, email, first and last name, gender, and a unique userId.
 */
public class User implements IModelElement{
    /**
     *
     */
    private String username; //TODO javadoc for every field
    private String password;
    private String email;
    private String first_name;
    private String last_name;
    private String gender;
    private String user_id;

    public User(String username,
                String password,
                String email,
                String first_name,
                String last_name,
                String gender,
                String user_id) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.user_id = user_id;
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
    public String getFirst_name() {
        return first_name;
    }

    /**
     * @return the last name of the user
     */
    public String getLast_name() {
        return last_name;
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
    public String getUser_id() {
        return user_id;
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

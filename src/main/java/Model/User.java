package Model;

public class User {
    private String user_name;
    private String password;
    private String email;
    private String first_name;
    private String last_name;
    private String gender;
    private String user_id;

    public User(String user_name,
                String password,
                String email,
                String first_name,
                String last_name,
                String gender,
                String user_id) {
        this.user_name = user_name;
        this.password = password;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getGender() {
        return gender;
    }

    public String getUser_id() {
        return user_id;
    }
}

package Model;

public class Person {
    private String person_id;
    private String associated_username;
    private String first_name;
    private String last_name;
    private String father_id;
    private String mother_id;
    private String spouse_id;

    public Person(String person_id,
                  String associated_username,
                  String first_name,
                  String last_name,
                  String father_id,
                  String mother_id,
                  String spouse_id) {
        this.person_id = person_id;
        this.associated_username = associated_username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.father_id = father_id;
        this.mother_id = mother_id;
        this.spouse_id = spouse_id;
    }

    public String getPerson_id() {
        return person_id;
    }

    public String getAssociated_username() {
        return associated_username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getFather_id() {
        return father_id;
    }

    public String getMother_id() {
        return mother_id;
    }

    public String getSpouse_id() {
        return spouse_id;
    }
}

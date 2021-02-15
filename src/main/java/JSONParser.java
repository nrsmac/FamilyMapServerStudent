import Model.Model;
import Model.User;
import Model.Person;
import Model.Event;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class JSONParser {
    private Model model;
    private ArrayList<User> users;
    private ArrayList<Person> persons;
    private ArrayList<Event> events;

    private JsonObject json;

    public JSONParser(Model model){
        this.model = model;
        parseModel();
        json = new JsonObject();
    }



    /**
     * Parses the member variable model into member ArrayLists of Users, Persons, and Events
     */
    private void parseModel() {

    }

    public JsonObject getJson(){
        //TODO: Watch gson lecture video. What should we be returning?
        return this.json;
    }
}

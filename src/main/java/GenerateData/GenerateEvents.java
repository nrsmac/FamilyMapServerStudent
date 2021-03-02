package GenerateData;

import Model.Event;
import Model.Person;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class GenerateEvents {
    private static final String LOCATIONS = "json/locations.json";

    /**
     * From 1000 to 9999;
     */
    private LinkedList<Integer> ids;

    private ArrayList<Person> persons;

    public ArrayList<Event> getEvents() {
        return events;
    }

    private ArrayList<Event> events;

    public GenerateEvents(ArrayList<Person> persons){
        this.persons = persons;
        this.events = new ArrayList<>();

        this.ids = new LinkedList<>();

        for (int i = 999; i<10000; i++){
            this.ids.add(i);
        }

        for (Person p : persons){
            generateBirth(p);
            generateBaptism(p);
            generateMarriage(p);
            generateDeath(p);
        }
    }

    private void generateBirth(Person person) {
        String eventId;
        String username = person.getUsername();
        String personId = person.getPersonId();
        String eventType = "birth";
        double latitude;
        double longitude;
        String country;
        String city;
        String year;

        Collections.shuffle(this.ids);
        int id = this.ids.pop();
        eventId = id+"";

        GeneratedLocation location = generateLocation();
        latitude=location.getLatitude();
        longitude=location.getLongitude();
        country = location.getCountry();
        city = location.getCity();

        Random rand = new Random();//TODO check parents birthdays
        year = rand.nextInt((2021-1600)+1)+1600 + "";

        Event event = new Event(eventId,username,personId,latitude,longitude,country,city,eventType,year);
        events.add(event);
    }
    private void generateDeath(Person person) {
        String eventId;
        String username = person.getUsername();
        String personId = person.getPersonId();
        String eventType = "death";
        double latitude;
        double longitude;
        String country;
        String city;
        String year;

        Collections.shuffle(this.ids);
        int id = this.ids.pop();
        eventId = id+"";

        GeneratedLocation location = generateLocation();
        latitude=location.getLatitude();
        longitude=location.getLongitude();
        country = location.getCountry();
        city = location.getCity();

        int birthYear = getBirthYear(person);

        Random rand = new Random();
        year = rand.nextInt((2021-birthYear)+1)+birthYear + "";


        Event event = new Event(eventId,username,personId,latitude,longitude,country,city,eventType,year);
        events.add(event);
    }

    private void generateMarriage(Person person) {
        Random rand = new Random();
        boolean isMarried = rand.nextBoolean();// 5050 chance of getting married
        if(isMarried){
            String eventId;
            String username = person.getUsername();
            String personId = person.getPersonId();
            String eventType = "marriage";
            double latitude;
            double longitude;
            String country;
            String city;
            String year;

            Collections.shuffle(this.ids);
            int id = this.ids.pop();
            eventId = id+"";

            int birthYear = getBirthYear(person);
            year = rand.nextInt((2021-birthYear+15)+1)+birthYear+15 + "";
        }
    }

    private void generateBaptism(Person person) {
        Random rand = new Random();
        boolean isBaptized = rand.nextBoolean();// 5050 chance of getting baptized
        if(isBaptized){
            String eventId;
            String username = person.getUsername();
            String personId = person.getPersonId();
            String eventType = "baptism";
            double latitude;
            double longitude;
            String country;
            String city;
            String year;

            Collections.shuffle(this.ids);
            int id = this.ids.pop();
            eventId = id+"";

            int birthYear = getBirthYear(person);
            year = rand.nextInt((2021-birthYear+8)+1)+birthYear+8 + "";
        }
    }



    private GeneratedLocation generateLocation() {
        Gson gson = new Gson();
        try(Reader locationsReader = new FileReader(LOCATIONS)) {
            GeneratedLocationData locationData = gson.fromJson(locationsReader,GeneratedLocationData.class);
            ArrayList<GeneratedLocation> locations = locationData.getLocations();
            Random rand = new Random();
            return locations.get(rand.nextInt(locations.size()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found while parsing JSON");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private int getBirthYear(Person person) {
        int birthYear = 0;
        for (Event event : this.events){
            if(event.getPerson_id() == person.getPersonId() && event.getEvent_type().equals("birth")){
                birthYear = Integer.parseInt(event.getYear());
            }
        }
        return birthYear;
    }
}

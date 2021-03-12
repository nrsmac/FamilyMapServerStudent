package GenerateData;

import Model.Event;
import Model.Person;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class GenerateEvents {
    private static final String LOCATIONS = "json/locations.json";

    /**
     * From 1000 to 9999;
     */
    private LinkedList<Integer> ids;


    public GenerateEvents(){

        this.ids = new LinkedList<>();

        for (int i = 999; i<10000; i++){
            this.ids.add(i);
        }

    }

    public Event generateBirth(Person person) {
        String eventId;
        String username = person.getAssociatedUsername();
        String personId = person.getPersonID();
        String eventType = "birth";
        double latitude;
        double longitude;
        String country;
        String city;
        int year;

        Collections.shuffle(this.ids);
        int id = this.ids.pop();
        eventId = id+"";

        GeneratedLocation location = generateLocation();
        latitude=location.getLatitude();
        longitude=location.getLongitude();
        country = location.getCountry();
        city = location.getCity();

        Random rand = new Random();//TODO check parents birthdays
        year = rand.nextInt((2021-1600)+1)+1600;

        return new Event(eventId,username,personId,latitude,longitude,country,city,eventType,year);
    }
    public Event generateDeath(Person person, int birthYear) {
        String eventId;
        String username = person.getAssociatedUsername();
        String personId = person.getPersonID();
        String eventType = "death";
        double latitude;
        double longitude;
        String country;
        String city;
        int year;

        Collections.shuffle(this.ids);
        int id = this.ids.pop();
        eventId = id+"";

        GeneratedLocation location = generateLocation();
        assert location != null;
        latitude=location.getLatitude();
        longitude=location.getLongitude();
        country = location.getCountry();
        city = location.getCity();


        Random rand = new Random();
        year = birthYear - 90;

        return new Event(eventId,username,personId,latitude,longitude,country,city,eventType,year);
    }

    public void generateMarriage(Person person, int birthYear) {
        Random rand = new Random();
        boolean isMarried = rand.nextBoolean();// 5050 chance of getting married
        if(isMarried){
            String eventId;
            String username = person.getAssociatedUsername();
            String personId = person.getPersonID();
            String eventType = "marriage";
            double latitude;
            double longitude;
            String country;
            String city;
            String year;

            GeneratedLocation location = generateLocation();
            assert location != null;
            latitude=location.getLatitude();
            longitude=location.getLongitude();
            country = location.getCountry();
            city = location.getCity();

            Collections.shuffle(this.ids);
            int id = this.ids.pop();
            eventId = id+"";

            year = rand.nextInt((2021-birthYear+15)+1)+birthYear+15 + "";
        }
    }

    public Event generateBaptism(Person person, int birthYear, int deathYear) {
        Random rand = new Random();
            String eventId;
            String username = person.getAssociatedUsername();
            String personId = person.getPersonID();
            String eventType = "baptism";
            double latitude;
            double longitude;
            String country;
            String city;
            int year;

            GeneratedLocation location = generateLocation();
            assert location != null;
         latitude=location.getLatitude();
        longitude=location.getLongitude();
        country = location.getCountry();
        city = location.getCity();

            Collections.shuffle(this.ids);
            int id = this.ids.pop();
            eventId = id+"";

            year = ThreadLocalRandom.current().nextInt(birthYear, deathYear);
        return new Event(eventId,username,personId,latitude,longitude,country,city,eventType,year);
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


    public HashSet<Event> marry(Person m, Person f, int year){

        HashSet<Event> marriages = new HashSet<>();
        Collections.shuffle(this.ids);
        int id = this.ids.pop();
        String eventId = id+"";

        GeneratedLocation location = generateLocation();
        assert location != null;
        Event marriageM = new Event(eventId,
                m.getAssociatedUsername(),
                m.getPersonID(),
                location.getLatitude(),
                location.getLongitude(),
                location.getCountry(),
                location.getCity(),
                "marriage",
                year);
        marriages.add(marriageM);

        Event marriageF = new Event(eventId,
                f.getAssociatedUsername(),
                f.getPersonID(),
                location.getLatitude(),
                location.getLongitude(),
                location.getCountry(),
                location.getCity(),
                "marriage",
                year);
        marriages.add(marriageF);

        m.setSpouseID(f.getPersonID());
        f.setSpouseID(m.getSpouseID());
        return marriages;
    }
}

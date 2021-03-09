package Response;

import Model.Event;

import java.util.ArrayList;

public class EventResponse {
    private ArrayList<Event> events;
    private boolean success;
    private String message;
    private String associatedUsername;
    private String eventID;
    private String personID;
    private double latitude;
    private double longitude;
    private String country;
    private String city;
    private String eventType;
    private int year;

    public EventResponse(ArrayList<Event> events, boolean success) {
        this.events = events;
        this.success = success;
    }

    public EventResponse(String associatedUsername,
                         String eventID,
                         String personID,
                         double latitude,
                         double longitude,
                         String country,
                         String city,
                         String eventType,
                         int year,
                         boolean success){

        this.associatedUsername = associatedUsername;
        this.eventID = eventID;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
        this.success = success;
    }

    public EventResponse(String message, boolean success){
        this.message = message;
        this.success = success;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}

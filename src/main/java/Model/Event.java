package Model;

import java.util.ArrayList;

/**
 * Represents a unique event which happened to a Person within a User's pedigree.
 */
public class Event implements IModelElement{
    private String eventId;
    private String username;
    private ArrayList<String> associatedPersonIds;
    private String latitude;
    private String longitude;
    private String country;
    private String city;
    private String eventType;
    private String year;

    public Event(String eventId, String username, ArrayList<String> associatedPersonIds, String latitude, String longitude, String country, String city, String eventType, String year) {
        this.eventId = eventId;
        this.username = username;
        this.associatedPersonIds = new ArrayList<>(associatedPersonIds);
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
    }

    /**
     * @return the unique id of this event
     */
    public String getEventId() {
        return eventId;
    }

    /**
     * @return the person associated with this event
     */
    public ArrayList<String> getAssociatedPersonIds() {
        return associatedPersonIds;
    }

    /**
     * @return the latitude where this event occurred
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * @return the longitude where this event occurred
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * @return the country where this event occurred
     */
    public String getCountry() {
        return country;
    }

    /**
     * @return where the city of the event occurred
     */
    public String getCity() {
        return city;
    }

    /**
     * @return the type of event this event is
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * @return the year the event occurred
     */
    public String getYear() {
        return year;
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

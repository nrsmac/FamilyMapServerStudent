package Model;

import java.util.ArrayList;

/**
 * Represents a unique event which happened to a Person within a User's pedigree.
 */
public class Event implements IModelElement{
    /**
     * Unique event id identifier. Primary key.
     */
    private String event_id;
    /**
     *
     */
    private String username;
    /**
     *
     */
    private ArrayList<String> associated_person_ids;
    /**
     *
     */
    private double latitude;
    /**
     *
     */
    private double longitude;
    /**
     *
     */
    private String country;
    /**
     *
     */
    private String city;
    /**
     *
     */
    private String event_type;
    /**
     *
     */
    private String year;

    public Event(String eventId, String username, ArrayList<String> associatedPersonIds, double latitude, double longitude, String country, String city, String event_type, String year) {
        this.event_id = eventId;
        this.username = username;
        this.associated_person_ids = new ArrayList<>(associatedPersonIds);
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.event_type = event_type;
        this.year = year;
    }

    /**
     * @return the unique id of this event
     */
    public String getEvent_id() {
        return event_id;
    }

    /**
     * @return the person associated with this event
     */
    public ArrayList<String> getAssociated_person_ids() {
        return associated_person_ids;
    }

    /**
     * @return the latitude where this event occurred
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @return the longitude where this event occurred
     */
    public double getLongitude() {
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
    public String getEvent_type() {
        return event_type;
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

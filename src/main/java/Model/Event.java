package Model;

/**
 * Represents a unique event which happened to a Person within a User's pedigree.
 */
public class Event implements IModelElement{
    private String person_id;
    /**
     * Unique event id identifier. Primary key.
     */
    private String event_id;
    /**
     *username associated with event
     */
    private String username;
    /**
     * type of event
     */
    private String event_type;
    /**
     * latitude where event took place
     */
    private double latitude;
    /**
     * longitude where event took place
     */
    private double longitude;
    /**
     * country where event took place
     */
    private String country;
    /**
     * city where event took place
     */
    private String city;
    /**
     * year event took place
     */
    private int year;

    public Event(String eventId, String username, String personId, double latitude, double longitude, String country, String city, String event_type, int year) {
        this.event_id = eventId;
        this.username = username;
        this.person_id = personId;
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
    public String getPerson_id(){return person_id;}

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
    public int getYear() {
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

    @Override
    public boolean equals(Object o){
        //TODO maybe more intense here
        if (o==null){
            return false;
        }
        if (o instanceof Event){
            Event oPerson = (Event) o;
            return oPerson.getEvent_id().equals(getEvent_id());
        } else {
            return false;
        }
    }
}

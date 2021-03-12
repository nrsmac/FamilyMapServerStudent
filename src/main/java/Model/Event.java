package Model;

/**
 * Represents a unique event which happened to a Person within a User's pedigree.
 */
public class Event implements IModelElement{
    private String personID;
    /**
     * Unique event id identifier. Primary key.
     */
    private String eventID;
    /**
     *username associated with event
     */
    private String associatedUsername;
    /**
     * type of event
     */
    private String eventType;
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

    public Event(String eventId,
                 String associatedUsername,
                 String personId,
                 double latitude,
                 double longitude,
                 String country,
                 String city,
                 String eventType,
                 int year) {
        this.eventID = eventId;
        this.associatedUsername = associatedUsername;
        this.personID = personId;
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
    public String getEventID() {
        return eventID;
    }

    /**
     * @return the person associated with this event
     */
    public String getPersonID(){return personID;}

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
    public String getEventType() {
        return eventType;
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
    public String getAssociatedUsername() {
        return this.associatedUsername;
    }

    public int hashCode(){
        return 0;
    }

    public String toString(){
        return "";
    }

    @Override
    public boolean equals(Object o){
        if (o==null){
            return false;
        }
        if (o instanceof Event){
            Event oPerson = (Event) o;
            return oPerson.getEventID().equals(getEventID());
        } else {
            return false;
        }
    }
}

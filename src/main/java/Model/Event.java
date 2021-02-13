package Model;

public class Event {
    private String event_id;
    private String associatedUsername;
    private String associatedPersonId;
    private String latitude;
    private String longitude;
    private String country;
    private String city;
    private String eventType;
    private String year;

    public Event(String event_id, String associatedUsername, String associatedPersonId, String latitude, String longitude, String country, String city, String eventType, String year) {
        this.event_id = event_id;
        this.associatedUsername = associatedUsername;
        this.associatedPersonId = associatedPersonId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
    }

    public String getEvent_id() {
        return event_id;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public String getAssociatedPersonId() {
        return associatedPersonId;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getEventType() {
        return eventType;
    }

    public String getYear() {
        return year;
    }
}

package GenerateData;

public class GeneratedLocation {

    private String country;
    private String city;
    private double latitude;
    private double longitude;

    public GeneratedLocation(String country, String city, double latitude, double longitude) {
        this.country = country;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}

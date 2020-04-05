package model;

public class CountryCityModel {
    private final String country;
    private final String city;

    public CountryCityModel(String country, String city) {
        this.country = country;
        this.city = city;
    }

    public String getCountry(){ return country; }
    public String getCity(){ return city; }
}

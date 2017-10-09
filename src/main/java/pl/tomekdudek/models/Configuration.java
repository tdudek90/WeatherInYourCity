package pl.tomekdudek.models;


public class Configuration {
    private final String APIID = "8a4c470863ee7aa88b2df10177c8eb54";
    private final String APIURL = "https://api.openweathermap.org/data/2.5/weather?q=";

    public String getAPIID() {
        return APIID;
    }

    public String getAPIURL() {
        return APIURL;
    }
}

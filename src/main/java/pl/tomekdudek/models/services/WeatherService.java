package pl.tomekdudek.models.services;


import pl.tomekdudek.models.Configuration;
import pl.tomekdudek.models.Utils;

public class WeatherService {
    private static WeatherService ourInstance = new WeatherService();

    public static WeatherService getService() {
        return ourInstance;
    }

    private WeatherService() {
    }

    private String apiUrl;
    private String apiId;

    public void makeCall(String city) {
        Configuration configuration = new Configuration();
        apiUrl = configuration.getAPIURL();
        apiId = configuration.getAPIID();

        apiUrl = apiUrl + city + "&appid=" + apiId;
        System.out.println(Utils.connectAndResponse(apiUrl));

    }

}

package pl.tomekdudek.models.services;


import org.json.JSONObject;
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

    private double temperature;
    private int pressure;
    private int humidity;

    public double getTemperature() {
        return temperature;
    }

    public double getTemperatureInCelsius(double temperature) {
        return Utils.changeKelvinToCelsius(temperature);
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void makeCall(String city) {
        Configuration configuration = new Configuration();
        apiUrl = configuration.getAPIURL();
        apiId = configuration.getAPIID();

        apiUrl = apiUrl + city + "&appid=" + apiId;
        parseJsonData(Utils.connectAndResponse(apiUrl));

    }

    public void parseJsonData(String data) {
        JSONObject object = new JSONObject(data);
        JSONObject mainObject = object.getJSONObject("main");
        temperature = mainObject.getDouble("temp");
        pressure = mainObject.getInt("pressure");
        humidity = mainObject.getInt("humidity");
        viewWeatherInformation(temperature,pressure,humidity);

    }

    public void viewWeatherInformation(double temperature, int pressure, int humidity){
        System.out.println("Temperature: " + Utils.changeKelvinToCelsius(temperature));
        System.out.println("Pressure: " + pressure);
        System.out.println("Humidity: " + humidity);
    }


}

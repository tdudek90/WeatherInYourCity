package pl.tomekdudek.models.services;

import javafx.application.Platform;
import org.json.JSONObject;
import pl.tomekdudek.models.Configuration;
import pl.tomekdudek.models.IWeatherObserver;
import pl.tomekdudek.models.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WeatherService {

    private String apiUrl;
    private String apiId;

    private double temperature;
    private int pressure;
    private int humidity;

    private List<IWeatherObserver> weatherObserver = new ArrayList<>();
    private ExecutorService executorService;

    private static WeatherService ourInstance = new WeatherService();

    public static WeatherService getService() {
        return ourInstance;
    }

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

    private WeatherService() {
        executorService = Executors.newSingleThreadExecutor();
    }


    public void makeCall(String city) {

        Configuration configuration = new Configuration();
        apiUrl = configuration.getAPIURL();
        apiId = configuration.getAPIID();

        apiUrl = apiUrl + city + "&appid=" + apiId;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                parseJsonData(Utils.connectAndResponse(apiUrl));
            }
        };
        executorService.execute(runnable);

    }

    public void parseJsonData(String jsonData) {
        JSONObject object = new JSONObject(jsonData);
        JSONObject mainObject = object.getJSONObject("main");
        temperature = mainObject.getDouble("temp");
        pressure = mainObject.getInt("pressure");
        humidity = mainObject.getInt("humidity");
        viewWeatherInformation(temperature, pressure, humidity);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                for (IWeatherObserver iWeatherObserver : weatherObserver) {
                    iWeatherObserver.onWeatherUpdate(new WeatherInfo(temperature, pressure, humidity));
                }
            }
        });
    }


    public void viewWeatherInformation(double temperature, int pressure, int humidity) {
        System.out.println("Temperature: " + Utils.changeKelvinToCelsius(temperature));
        System.out.println("Pressure: " + pressure);
        System.out.println("Humidity: " + humidity);
    }

    public void registerObserver(IWeatherObserver weatherObserver) {
        this.weatherObserver.add(weatherObserver);
    }


}

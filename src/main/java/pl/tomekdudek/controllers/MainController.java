package pl.tomekdudek.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import pl.tomekdudek.models.IWeatherObserver;
import pl.tomekdudek.models.Utils;
import pl.tomekdudek.models.services.WeatherInfo;
import pl.tomekdudek.models.services.WeatherService;

import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable, IWeatherObserver {

    @FXML
    private Button showWeatherButton;

    @FXML
    private TextField cityTextField;

    private WeatherService weatherService = WeatherService.getService();

    @FXML
    private Label viewWeatherLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        weatherService.registerObserver(this);
        showWeatherButton.setOnMouseClicked(e -> {
            weatherService.makeCall(cityTextField.getText());
            cityTextField.clear();
        });

        cityTextField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                weatherService.makeCall(cityTextField.getText());
                cityTextField.clear();
            }
        });
    }

    @Override
    public void onWeatherUpdate(WeatherInfo weatherInfo) {
        viewWeatherLabel.setText("Temperature: " + Utils.changeKelvinToCelsius(weatherInfo.getTemperature()) +
                "\n" + "Pressure: " + weatherInfo.getPressure() +
                "\n" + "Humidity: " + weatherInfo.getHumidity());
    }

}

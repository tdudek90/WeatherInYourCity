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

    @FXML
    private TextField cityID;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        weatherService.registerObserver(this);
        showWeatherButton.setOnMouseClicked(e -> {
            if (!cityTextField.getText().isEmpty() && cityTextField.getText().length() > 2) {
                weatherService.makeCall(cityTextField.getText());
                cityTextField.clear();
            } else if (!cityID.getText().isEmpty() && cityID.getText().length() > 2) {
                weatherService.makeCall(Integer.parseInt(cityID.getText()));
            } else {
                Utils.showAlert("ERROR", "Wrong city name or ID");
            }
        });

        cityTextField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                weatherService.makeCall(cityTextField.getText());
                cityTextField.clear();
            }
        });

        cityID.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                weatherService.makeCall(Integer.parseInt(cityID.getText()));
                cityID.clear();
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

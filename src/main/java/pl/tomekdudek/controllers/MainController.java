package pl.tomekdudek.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import pl.tomekdudek.models.services.WeatherService;

import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {

    @FXML
    private Button showWeatherButton;

    @FXML
    private TextField cityTextField;

    private WeatherService weatherService = WeatherService.getService();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
}

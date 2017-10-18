package pl.tomekdudek.models;

import pl.tomekdudek.models.services.WeatherInfo;

/**
 * Created by Tomek on 2017-10-18.
 */
public interface IWeatherObserver {
    void onWeatherUpdate(WeatherInfo weatherInfo);
}

package pl.tomekdudek.models.services;

/**
 * Created by Tomek on 2017-10-12.
 */
public class WeatherInfo {

    private double temperature;
    private int pressure;
    private int humidity;
    private String city;

    private WeatherInfo(Builder builder) {
        temperature = builder.temperature;
        pressure = builder.pressure;
        humidity = builder.humidity;
        city = builder.city;

    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public static class Builder {
        private double temperature;
        private int pressure;
        private int humidity;
        private String city;

        public Builder(String city) {
            this.city = city;
        }

        public Builder temperature(double temperature) {
            this.temperature = temperature;
            return this;
        }

        public Builder pressure(int pressure) {
            this.pressure = pressure;
            return this;
        }

        public Builder humidity(int humidity) {
            this.humidity = humidity;
            return this;
        }

        public WeatherInfo build() {
            return new WeatherInfo(this);
        }

    }
}

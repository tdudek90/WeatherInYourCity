package pl.tomekdudek.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class Utils {

    public  static String connectAndResponse(String url) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            URLConnection connection = new URL(url).openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while((line = reader.readLine()) != null){
                stringBuffer.append(line);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuffer.toString();
    }

    public static double changeKelvinToCelsius(double temperature){
        return temperature - 273.15;
    }
}

package se.miun.krsa1201.bathingsites;

import android.graphics.drawable.Drawable;

import java.io.InputStream;
import java.net.URL;

/**
 * Simple class to contain all the different values obtained from the API request.
 * Example endpoint: http://dt031g.programvaruteknik.nu/badplatser/weather.php
 */
public final class LocationWeatherData {
    private String address;
    private String lat;
    private String lon;
    private String condition;
    private String tempC;
    private String humidity;
    private String windDegrees;
    private String windKph;
    private String imageLink;
    private Drawable imageDrawable;

    public LocationWeatherData() {}

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getTempC() {
        return tempC + "°";
    }

    public void setTempC(String tempC) {
        this.tempC = tempC;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWindDegrees() {
        return windDegrees;
    }

    public void setWindDegrees(String windDegrees) {
        this.windDegrees = windDegrees;
    }

    public String getWindKph() {
        return windKph;
    }

    public void setWindKph(String windKph) {
        this.windKph = windKph;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImage(String imageLink) {
        this.imageLink = imageLink;
        try {
            InputStream is = (InputStream) new URL(imageLink).getContent();
            imageDrawable = Drawable.createFromStream(is, "weather");
        } catch (Exception e) {
            System.out.println("Unable to set image link");
        }
    }

    public Drawable getImageDrawable() {
        return imageDrawable;
    }
}

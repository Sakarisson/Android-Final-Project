package se.miun.krsa1201.bathingsites;

/**
 * Simple class to contain all the different values obtained from the API request.
 * Example endpoint: http://dt031g.programvaruteknik.nu/badplatser/weather.php
 */
public final class LocationWeatherData {
    private String _address;
    private String _lat;
    private String _lon;
    private String _condition;
    private String _temp_c;
    private String _humidity;
    private String _wind_degrees;
    private String _wind_kph;
    private String _image;

    public LocationWeatherData() {}

    public String getAddress() {
        return _address;
    }

    public void setAddress(String _address) {
        this._address = _address;
    }

    public String getLat() {
        return _lat;
    }

    public void setLat(String _lat) {
        this._lat = _lat;
    }

    public String getLon() {
        return _lon;
    }

    public void setLon(String _lon) {
        this._lon = _lon;
    }

    public String getCondition() {
        return _condition;
    }

    public void setCondition(String _condition) {
        this._condition = _condition;
    }

    public String getTemp_c() {
        return _temp_c;
    }

    public void setTempC(String _temp_c) {
        this._temp_c = _temp_c;
    }

    public String getHumidity() {
        return _humidity;
    }

    public void setHumidity(String _humidity) {
        this._humidity = _humidity;
    }

    public String getWindDegrees() {
        return _wind_degrees;
    }

    public void setWindDegrees(String _wind_degrees) {
        this._wind_degrees = _wind_degrees;
    }

    public String getWindKph() {
        return _wind_kph;
    }

    public void setWindKph(String _wind_kph) {
        this._wind_kph = _wind_kph;
    }

    public String getImage() {
        return _image;
    }

    public void setImage(String _image) {
        this._image = _image;
    }
}

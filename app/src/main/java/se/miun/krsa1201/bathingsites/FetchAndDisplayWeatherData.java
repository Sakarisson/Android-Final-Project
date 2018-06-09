package se.miun.krsa1201.bathingsites;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public final class FetchAndDisplayWeatherData extends AsyncTask<String, Integer, String> {
    private Context context;
    private URL apiEndpoint;
    private LocationWeatherData data;

    public FetchAndDisplayWeatherData(Context context) {
        this.context = context;
        data = new LocationWeatherData();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... apiEndpointUrl) {
        try {
            apiEndpoint = new URL(apiEndpointUrl[0]);
            HttpURLConnection conn = (HttpURLConnection) apiEndpoint.openConnection();
            conn.setConnectTimeout(60000); // timing out in a minute

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            data.setAddress(trimLine(in.readLine()));
            data.setLat(trimLine(in.readLine()));
            data.setLon(trimLine(in.readLine()));
            data.setCondition(trimLine(in.readLine()));
            data.setTempC(trimLine(in.readLine()));
            data.setHumidity(trimLine(in.readLine()));
            data.setWindDegrees(trimLine(in.readLine()));
            data.setWindKph(trimLine(in.readLine()));
            data.setImage(trimLine(in.readLine()));


            in.close();
        } catch (Exception e) {
            System.out.println("Unable to fetch weather data");
            System.out.println(e.getMessage());
            return null;
        }
        return "Done.";
    }

    private String trimLine(String line) {
        int firstColonIndex = line.indexOf(':') + 1;
        int breakLineStart = line.length() - 4;
        return line.substring(firstColonIndex, breakLineStart);
    }
}

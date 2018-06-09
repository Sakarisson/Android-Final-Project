package se.miun.krsa1201.bathingsites;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.WeakHashMap;

public final class FetchAndDisplayWeatherData extends AsyncTask<String, Integer, LocationWeatherData> {
    private Context context;
    private View rootView;
    private URL apiEndpoint;
    private LocationWeatherData data;

    public FetchAndDisplayWeatherData(Context context, View rootView) {
        this.context = context;
        this.rootView = rootView;
        data = new LocationWeatherData();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected LocationWeatherData doInBackground(String... apiEndpointUrl) {
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
        return data;
    }

    protected void onPostExecute(LocationWeatherData data) {
        int a = 1;
    }

    private String trimLine(String line) {
        int firstColonIndex = line.indexOf(':') + 1;
        int breakLineStart = line.length() - 4;
        return line.substring(firstColonIndex, breakLineStart);
    }
}

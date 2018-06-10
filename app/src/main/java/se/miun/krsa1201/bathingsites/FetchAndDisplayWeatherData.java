package se.miun.krsa1201.bathingsites;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Simple asyncTask class that fetches the current weather from a certain URL.
 * The data will be populated in the LocationWeatherData class.
 * At the moment, the data needs to be formatted exactly like in the given assignment
 * URL, meaning the same exact order. In hindsight, I should have some sort of key-value
 * list to fetch the individual values. This would have made it still work correctly no
 * matter the order of the list.
 */
public final class FetchAndDisplayWeatherData extends AsyncTask<String, Integer, LocationWeatherData> {
    private Context context;
    private LayoutInflater inflater;
    private ProgressDialog loadingDialog;
    private LocationWeatherData data;

    public FetchAndDisplayWeatherData(Context context, LayoutInflater inflater) {
        this.context = context;
        this.inflater = inflater;
        data = new LocationWeatherData();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        loadingDialog = new ProgressDialog(context);
        loadingDialog.setTitle("Getting current weather...");
        loadingDialog.show();
    }

    @Override
    protected LocationWeatherData doInBackground(String... apiEndpointUrl) {
        try {
            URL apiEndpoint = new URL(apiEndpointUrl[0]);
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
        loadingDialog.dismiss();
        addWeatherToView(data, inflater);
    }

    private void addWeatherToView(LocationWeatherData data, LayoutInflater inflater) {
        View weatherView;
        if (data == null) {
            weatherView = inflater.inflate(R.layout.current_weather_unavailable_dialog, null);
        } else {
            weatherView = inflater.inflate(R.layout.current_weather_dialog, null);
            TextView condition = weatherView.findViewById(R.id.weather_visibility);
            condition.setText(data.getCondition());
            TextView degrees = weatherView.findViewById(R.id.weather_degrees);
            degrees.setText(data.getTempC());
            ImageView weatherImage = weatherView.findViewById(R.id.weather_image);
            weatherImage.setImageDrawable(data.getImageDrawable());
        }
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setView(weatherView);
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    private String trimLine(String line) throws Exception {
        boolean addressField = line.contains("address");
        int firstColonIndex = line.indexOf(':') + 1;
        int breakLineStart = line.length() - 4;
        String value = line.substring(firstColonIndex, breakLineStart);
        if (value.equals("") && !addressField) {
            throw new Exception("Unable to get values");
        }
        return value;
    }
}

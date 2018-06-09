package se.miun.krsa1201.bathingsites;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.WeakHashMap;

public final class FetchAndDisplayWeatherData extends AsyncTask<String, Integer, LocationWeatherData> {
    private Context context;
    private View weatherLayout;
    private URL apiEndpoint;
    private LocationWeatherData data;

    public FetchAndDisplayWeatherData(Context context, View weatherLayout) {
        this.context = context;
        this.weatherLayout = weatherLayout;
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
        addWeatherToView(data, weatherLayout);
    }

    private void addWeatherToView(LocationWeatherData data, View view) {
        TextView condition = view.findViewById(R.id.weather_visibility);
        condition.setText(data.getCondition());
        TextView degrees = view.findViewById(R.id.weather_degrees);
        degrees.setText(data.getTempC());
        ImageView weatherImage = view.findViewById(R.id.weather_image);
        weatherImage.setImageDrawable(data.getImageDrawable());
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setView(view);
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    private String trimLine(String line) {
        int firstColonIndex = line.indexOf(':') + 1;
        int breakLineStart = line.length() - 4;
        return line.substring(firstColonIndex, breakLineStart);
    }
}

package se.miun.krsa1201.bathingsites;

import android.app.AlertDialog;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

/**
 * A placeholder fragment containing a simple view.
 */
public class NewBathingSiteFragment extends Fragment {
    private EditText name;
    private EditText description;
    private EditText address;
    private EditText latitude;
    private EditText longitude;
    private RatingBar grade;
    private EditText waterTemp;
    private EditText dateForTemp;

    private View view;

    private TextWatcher locationChangedWatcher;

    public void clear() {
        name.setText("");
        description.setText("");
        address.setText("");
        latitude.setText("");
        longitude.setText("");
        grade.setRating(0);
        waterTemp.setText("");
        dateForTemp.setText("");
    }

    public void save() {
        boolean valid = isValidForm();
        if (valid) {
            showSnackbar();
        }
    }

    /**
     * According to the assignment, the coordinates should be given as latitude|latitude.
     * To me this seems odd, so I have in this implementation made the assumption that
     * there was a mistake in the specification.
     */
    private String coordinatesString() {
        return latitude.getText().toString() + "|" + longitude.getText().toString();
    }

    public void showWeather() {
        LayoutInflater inflater = getLayoutInflater();
        try {
            View weatherLayout = inflater.inflate(R.layout.current_weather_dialog, null);
            String url = "http://dt031g.programvaruteknik.nu/badplatser/weather.php";
            if (coordinatesSet() || !address.getText().toString().equals("")) {
                url += "?location=";
                url += coordinatesSet() ? coordinatesString() : address.getText().toString();
            } else {
                throw new Exception("Invalid location");
            }
            FetchAndDisplayWeatherData task = new FetchAndDisplayWeatherData(getContext(), inflater);
            task.execute(url);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void showSnackbar() {
        Snackbar snackbar = Snackbar.make(view, getSiteText(), Snackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();

        TextView textView = snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setMaxLines(8);
        snackbar.show();
    }

    private String getSiteText() {
        String siteText = "";
        siteText += "Name: " + getFieldText(name) + "\n";
        siteText += "Description: " + getFieldText(description) + "\n";
        siteText += "Address: " + getFieldText(address) + "\n";
        siteText += "Coordinates: " + getFieldText(latitude) + ", " + getFieldText(longitude) + "\n";
        siteText += "Grade: " + grade.getRating() + "\n";
        siteText += "Water temp: " + getFieldText(waterTemp) + "\n";
        siteText += "Date for temp" + getFieldText(dateForTemp) + "\n";
        return siteText;
    }

    private String getFieldText(EditText field) {
        return field.getText().toString();
    }

    private void init() {
        locationChangedWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                boolean addressSet = !address.getText().toString().equals("");
                boolean coordinatesSet = coordinatesSet();
                if (!addressSet && !coordinatesSet) {
                    return;
                }
                address.setError(null);
                latitude.setError(null);
                longitude.setError(null);
            }
        };
        name = view.findViewById(R.id.bathing_site_name_input_field);
        description = view.findViewById(R.id.bathing_site_description_input_field);
        address = view.findViewById(R.id.bathing_site_address_input_field);
        address.addTextChangedListener(locationChangedWatcher);
        latitude = view.findViewById(R.id.bathing_site_latitude_input_field);
        latitude.addTextChangedListener(locationChangedWatcher);
        longitude = view.findViewById(R.id.bathing_site_longitude_input_field);
        longitude.addTextChangedListener(locationChangedWatcher);
        grade = view.findViewById(R.id.bathing_site_rating_field);
        waterTemp = view.findViewById(R.id.bathing_site_water_temp_input_field);
        dateForTemp = view.findViewById(R.id.bathing_site_date_for_temp_input_field);
        showSnackbar();
    }

    private boolean coordinatesSet() {
        return !(latitude.getText().toString().equals("") || longitude.getText().toString().equals(""));
    }

    // This returns true if form is valid according to assignment
    // specifications. It acts as clientside form validation.
    private boolean isValidForm() {
        boolean nameIsSet = !name.getText().toString().equals("");
        boolean coordinatesAreSet = coordinatesSet();
        boolean addressIsSet = !address.getText().toString().equals("");
        if (!nameIsSet) {
            name.setError("Name is required");
        }
        if ((!addressIsSet && !coordinatesAreSet)) {
            String message = "Address or coordinates required";
            address.setError(message);
            latitude.setError(message);
            longitude.setError(message);
        }
        return (nameIsSet && (coordinatesAreSet || addressIsSet));
    }

    public NewBathingSiteFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_new_bathing_site, container, false);
        if (view != null) {
            init();
        }
        return view;
    }
}

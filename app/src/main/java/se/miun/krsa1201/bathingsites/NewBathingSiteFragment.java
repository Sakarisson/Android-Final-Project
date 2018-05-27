package se.miun.krsa1201.bathingsites;

import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;

/**
 * A placeholder fragment containing a simple view.
 */
public class NewBathingSiteFragment extends Fragment {
    private EditText _name;
    private EditText _description;
    private EditText _address;
    private EditText _latitude;
    private EditText _longitude;
    private RatingBar _grade;
    private EditText _waterTemp;
    private EditText _dateForTemp;

    private View _view;

    private TextWatcher _locationChangedWatcher;

    public void clear() {
        _name.setText("");
        _description.setText("");
        _address.setText("");
        _latitude.setText("");
        _longitude.setText("");
        _grade.setRating(0);
        _waterTemp.setText("");
        _dateForTemp.setText("");
    }

    public void save() {
        boolean valid = isValidForm();
        if (valid) {
            showSnackbar();
        }
    }

    private void showSnackbar() {
        Snackbar snackbar = Snackbar.make(_view, "myCoordinatorLayout", Snackbar.LENGTH_LONG);
        snackbar.setText(getSiteText());
        snackbar.show();
    }

    private String getSiteText() {
        String siteText = "";
        siteText += "Name: " + getFieldText(_name) + "\n";
        siteText += "Description: " + getFieldText(_description) + "\n";
        siteText += "Address: " + getFieldText(_address) + "\n";
        siteText += "Coordinates: " + getFieldText(_latitude) + ", " + getFieldText(_longitude) + "\n";
        siteText += "Grade: " + _grade.getRating() + "\n";
        siteText += "Water temp: " + getFieldText(_waterTemp) + "\n";
        siteText += "Date for temp" + getFieldText(_dateForTemp) + "\n";
        return siteText;
    }

    private String getFieldText(EditText field) {
        return field.getText().toString();
    }

    private void init() {
        _locationChangedWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                boolean addressSet = !_address.getText().toString().equals("");
                boolean coordinatesSet = coordinatesSet();
                if (!addressSet && !coordinatesSet) {
                    return;
                }
                _address.setError(null);
                _latitude.setError(null);
                _longitude.setError(null);
            }
        };
        _name = _view.findViewById(R.id.bathing_site_name_input_field);
        _description = _view.findViewById(R.id.bathing_site_description_input_field);
        _address = _view.findViewById(R.id.bathing_site_address_input_field);
        _address.addTextChangedListener(_locationChangedWatcher);
        _latitude = _view.findViewById(R.id.bathing_site_latitude_input_field);
        _latitude.addTextChangedListener(_locationChangedWatcher);
        _longitude = _view.findViewById(R.id.bathing_site_longitude_input_field);
        _longitude.addTextChangedListener(_locationChangedWatcher);
        _grade = _view.findViewById(R.id.bathing_site_rating_field);
        _waterTemp = _view.findViewById(R.id.bathing_site_water_temp_input_field);
        _dateForTemp = _view.findViewById(R.id.bathing_site_date_for_temp_input_field);
        showSnackbar();
    }

    private boolean coordinatesSet() {
        return !(_latitude.getText().toString().equals("") || _longitude.getText().toString().equals(""));
    }

    // This returns true if form is valid according to assignment
    // specifications. It acts as clientside form validation.
    private boolean isValidForm() {
        boolean nameIsSet = !_name.getText().toString().equals("");
        boolean coordinatesAreSet = coordinatesSet();
        boolean addressIsSet = !_address.getText().toString().equals("");
        if (!nameIsSet) {
            _name.setError("Name is required");
        }
        if ((!addressIsSet && !coordinatesAreSet)) {
            String message = "Address or coordinates required";
            _address.setError(message);
            _latitude.setError(message);
            _longitude.setError(message);
        }
        return (nameIsSet && (coordinatesAreSet || addressIsSet));
    }

    public NewBathingSiteFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        _view = inflater.inflate(R.layout.fragment_new_bathing_site, container, false);
        if (_view != null) {
            init();
        }
        return _view;
    }
}

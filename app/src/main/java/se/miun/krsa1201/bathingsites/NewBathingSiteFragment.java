package se.miun.krsa1201.bathingsites;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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

    private void init() {
        _name = _view.findViewById(R.id.bathing_site_name_input_field);
        _description = _view.findViewById(R.id.bathing_site_description_input_field);
        _address = _view.findViewById(R.id.bathing_site_address_input_field);
        _latitude = _view.findViewById(R.id.bathing_site_latitude_input_field);
        _longitude = _view.findViewById(R.id.bathing_site_longitude_input_field);
        _grade = _view.findViewById(R.id.bathing_site_rating_field);
        _waterTemp = _view.findViewById(R.id.bathing_site_water_temp_input_field);
        _dateForTemp = _view.findViewById(R.id.bathing_site_date_for_temp_input_field);
    }

    // TODO
    private void addRequiredWarningToEditTextField(EditText field) {
        Drawable x = _view.getResources().getDrawable(R.drawable.ic_launcher_background);
        x.setBounds(0, 0, x.getIntrinsicWidth(), x.getIntrinsicHeight());
        field.setCompoundDrawables(null, null, x, null);
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

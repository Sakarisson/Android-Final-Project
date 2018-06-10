package se.miun.krsa1201.bathingsites;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public final class SettingsActivity extends AppCompatActivity {
    private SharedPreferences apiPreferences;
    private String defaultApiEndpoint = "http://dt031g.programvaruteknik.nu/badplatser/weather.php";

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.new_bathing_site_settings);
        init();
    }

    private void init() {
        apiPreferences = getSharedPreferences("API", Context.MODE_PRIVATE);
        final EditText endpointInput = findViewById(R.id.new_bathing_site_settings_url_field);
        endpointInput.setText(getUrlPreference());

        endpointInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setUrlPreference(s.toString());
            }
        });

        Button resetButton = findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUrlPreference(defaultApiEndpoint);
                endpointInput.setText(getUrlPreference());
            }
        });
    }

    private void setUrlPreference(String url) {
        SharedPreferences.Editor editor = apiPreferences.edit();
        editor.putString("ENDPOINT_URL", url);
        editor.apply();
    }

    private String getUrlPreference() {
        return apiPreferences.getString("ENDPOINT_URL", defaultApiEndpoint);
    }
}

package se.miun.krsa1201.bathingsites;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public final class NewBathingSiteSettings extends AppCompatActivity {
    private SharedPreferences sp;
    private String selectedEndpoint = null;

    private String defaultUrl = "http://dt031g.programvaruteknik.nu/badplatser/weather.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_bathing_site_settings);
        sp = getSharedPreferences("API", Context.MODE_PRIVATE);
        selectedEndpoint = sp.getString("ENDPOINT_URL", defaultUrl);
        int a = 1;
    }
}

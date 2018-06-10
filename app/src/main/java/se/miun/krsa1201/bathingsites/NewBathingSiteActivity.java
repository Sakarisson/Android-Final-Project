package se.miun.krsa1201.bathingsites;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public final class NewBathingSiteActivity extends AppCompatActivity {
    Fragment newBathingSiteFragment;

    private void init() {
        newBathingSiteFragment = getSupportFragmentManager().findFragmentById(R.id.new_bathing_site_fragment);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_bathing_site);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_new_bathing_site, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_clear:
                ((NewBathingSiteFragment) newBathingSiteFragment).clear();
                return true;

            case R.id.action_save:
                ((NewBathingSiteFragment) newBathingSiteFragment).save();
                return true;

            case R.id.action_weather:
                ((NewBathingSiteFragment) newBathingSiteFragment).showWeather();
                return true;

            case R.id.action_settings:
                ((NewBathingSiteFragment) newBathingSiteFragment).showSettings();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}

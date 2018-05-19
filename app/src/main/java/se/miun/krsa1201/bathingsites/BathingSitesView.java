package se.miun.krsa1201.bathingsites;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

public final class BathingSitesView extends LinearLayout {
    private int _numberOfSites;
    private TextView _numberOfSitesText;
    private String _sitesString;
    private Context _context;
    private AttributeSet _attrs;

    public BathingSitesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        _context = context;
        _attrs = attrs;
        _sitesString = context.getResources().getString(R.string.x_number_of_bathing_sites);
        init();
    }

    private void init() {
        setNumberOfSites(0);
        inflate(_context, R.layout.bathing_sites_view, this);
        _numberOfSitesText = findViewById(R.id.bathing_sites_number);
        setText();
    }

    private void setText() {
        String outputText = _numberOfSites + " " + _sitesString;
        _numberOfSitesText.setText(outputText);
    }

    public void setNumberOfSites(int numberOfSites) {
        _numberOfSites = numberOfSites;
    }

    public int getNumberOfSites() {
        return _numberOfSites;
    }
}

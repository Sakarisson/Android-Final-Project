package se.miun.krsa1201.bathingsites;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public final class BathingSitesView extends View {
    private int _numberOfSites;

    public BathingSitesView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void init() {
        _numberOfSites = 0;
    }

    public void setNumberOfSites(int numberOfSites) {
        _numberOfSites = numberOfSites;
    }
}

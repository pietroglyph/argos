package com.spartronics4915.argos.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.spartronics4915.argos.R;

/**
 * Fragment that holds settings.
 */

public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
    }
}

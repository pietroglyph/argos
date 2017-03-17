package com.spartronics4915.argos.match;

import android.util.Log;
import android.view.View;

import com.google.firebase.crash.FirebaseCrash;
import com.spartronics4915.argos.Argos;

import static android.content.ContentValues.TAG;

/**
 * So we can pass our match key to the click listener for our match recycler listitem.
 */

public class MatchOnClickListener implements View.OnClickListener {

    Argos mApplication;
    String mKey;

    public MatchOnClickListener(Argos application, String key) {
        mApplication = application;
        mKey = key;
    }

    @Override
    public void onClick(View v) {
        Log.println(Log.DEBUG, "Match Select: ", "Match clicked.");
        try {
            mApplication.setMatchRef(mKey);
        } catch (Exception e) {
            FirebaseCrash.report(e); // Report the exception to the Firebase Crash Reporting service.
        }
    }
}

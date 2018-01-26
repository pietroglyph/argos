package com.spartronics4915.argos.event;

import android.util.Log;
import android.view.View;

import com.google.firebase.crash.FirebaseCrash;
import com.spartronics4915.argos.Argos;

/**
 * So we can pass our match key to the click listener for our match recycler listitem.
 */

public class EventOnClickListener implements View.OnClickListener {

    Argos mApplication;
    String mKey;

    public EventOnClickListener(Argos application, String key) {
        mApplication = application;
        mKey = key;
    }

    @Override
    public void onClick(View v) {
        Log.println(Log.DEBUG, "Event Select: ", "Event clicked.");
        try {
            mApplication.setEventRef(mKey);
        } catch (Exception e) {
            FirebaseCrash.report(e); // Report the exception to the Firebase Crash Reporting service.
        }
    }
}

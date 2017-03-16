package com.spartronics4915.argos;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Basically a constructor for anyone making/getting a match and a empty constructor for Firebase.
 * Then just a bunch of getters and setters for accessing the data.
 */

public class Match {
    private String mName;
    private String mStartDate;

    public Match() {
        // Needed for Firebase
    }

    public Match(String name, String startDate) {
        mName = name;
        mStartDate = startDate;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getStartDate() {
        return mStartDate;
    }

    public void setStartDate(String date) {
        mStartDate = date;
    }
}

package com.spartronics4915.argos.event;

/**
 * Basically a constructor for anyone making/getting a match and a empty constructor for Firebase.
 * Then just a bunch of getters and setters for accessing the data.
 */

public class Event {
    private String mName;
    private String mStartDate;

    public Event() {
        // Needed for Firebase
    }

    public Event(String name, String startDate) { // These parameters correspond to data fields of each match
        mName = name;
        mStartDate = startDate;
    }

    // Must have getters *and* setters
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

package com.spartronics4915.argos;

import android.app.Application;
import android.content.SharedPreferences;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Top-level class for storing globally persistent data and objects.
 */

public class Argos extends Application {

    private FirebaseDatabase mDatabase;
    private FirebaseAuth mAuth;

    private SharedPreferences mSharedPrefs;

    // Global references
    // A reference is made global to ensure that it can be set and gotten consistently, anywhere.
    // More importantly, they are global to ensure that the data is persisted if it is part of our app's state
    private DatabaseReference mEventRef;
    private DatabaseReference mSeasonRef;

    public Argos() {
        // Nothing to do here, it all _should_ get called on onCreate.
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(getApplicationContext()); // Make sure that Firebase is initialized
        mDatabase = FirebaseDatabase.getInstance(); // Get an instance of the database to be used by everyone.
        mDatabase.setPersistenceEnabled(true); // All data fetched from the database while online should be saved and persist on disk when offline.
        mDatabase.setPersistenceCacheSizeBytes(100000000); // 100MB Cache Size
        mAuth = FirebaseAuth.getInstance(); // Get an instance of the auth object to be used by everyone.
    }

    public void setEventRef(String refKey) throws Exception {
        mEventRef = mDatabase.getReference("gamedata/data/"+refKey);
        if (mEventRef == null) {
            throw new Exception("Couldn't set event reference.");
        }
    }

    public void setSeasonRef(String refKey) throws Exception {
        mEventRef = mDatabase.getReference("gamedata/meta/"+refKey);
        if (mEventRef == null) {
            throw new Exception("Couldn't set season reference.");
        }
    }

    public DatabaseReference getEventRef() {
        return mEventRef;
    }

    public FirebaseDatabase getDatabase() {
        return mDatabase;
    }

    public FirebaseAuth getAuth() {
        return mAuth;
    }

}

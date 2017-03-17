package com.spartronics4915.argos;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by declan on 3/16/2017.
 */

public class Argos extends Application {

    private FirebaseDatabase mDatabase; // Should this be a static globally accessible field?
    private FirebaseAuth mAuth;

    private DatabaseReference mMatchRef;

    public Argos() {
        // Nothing to do here, it all _should_ get called on onCreate.
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(getApplicationContext()); // Make sure that Firebase is initialized
        mDatabase = FirebaseDatabase.getInstance(); // Get an instance of the database to be used by everyone.\
        mDatabase.setPersistenceEnabled(true); // All data fetched from the database while online should be saved and persist on disk when offline.
        mDatabase.setPersistenceCacheSizeBytes(100000000); // 100MB Cache Size
        mAuth = FirebaseAuth.getInstance(); // Get an instance of the auth object to be used by everyone.
    }

    public void setMatchRef(String refKey) throws Exception {
        mMatchRef = mDatabase.getReference("gamedata/matchdata/"+refKey); // If this is an invalid reference this will throw an exception
        if (mMatchRef == null) {
            throw new Exception("Invalid reference key.");
        }
    }

    public DatabaseReference getMatchRef() {
        return mMatchRef;
    }

    public FirebaseDatabase getDatabase() {
        return mDatabase;
    }

    public FirebaseAuth getAuth() {
        return mAuth;
    }

}

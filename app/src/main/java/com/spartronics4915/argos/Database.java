package com.spartronics4915.argos;

import com.google.firebase.database.FirebaseDatabase;

/**
 * We may need this to have a global FirebaseDatabase so we have only one Match reference that is
 * storing data offline, but I'm looking for the most idiomatic way to do this.
 */

public class Database {
    private FirebaseDatabase mDatabase; // Should encapsulation be used here, or should this be public and static (we don't have a setter)?
    public Database() {
        mDatabase = FirebaseDatabase.getInstance();
    }

    public FirebaseDatabase getDatabase() {
        return mDatabase;
    }

}

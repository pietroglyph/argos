package com.spartronics4915.argos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;
import com.spartronics4915.argos.Argos;
import com.spartronics4915.argos.event.Event;
import com.spartronics4915.argos.event.EventHolder;
import com.spartronics4915.argos.event.EventOnClickListener;
import com.spartronics4915.argos.R;

public class EventActivity extends AppCompatActivity {
    private FirebaseDatabase mDatabase;
    private Argos mApplication;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Put top-level objects into member variables
        mApplication = ((Argos) this.getApplication()); // Get the top-level class for sharing data
        mDatabase = mApplication.getDatabase();

        setContentView(R.layout.activity_event); // Set the content in the activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); // Get the toolbar
        setSupportActionBar(toolbar); // Set the toolbar

        // Make the recycler
        RecyclerView recycler = (RecyclerView) findViewById(R.id.recycler_matches);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        // Add events to the recycler and add an onClickEvent to them (See EventOnClickListener.java)
        mAdapter = new FirebaseRecyclerAdapter<Event, EventHolder>(Event.class, android.R.layout.simple_list_item_2, EventHolder.class, FirebaseDatabase.getInstance().getReference("gamedata").child("matchmeta")) {
            @Override
            public void populateViewHolder(EventHolder matchViewHolder, Event event, int position) {
                matchViewHolder.setName(event.getName());
                matchViewHolder.setText(event.getStartDate());
                matchViewHolder.itemView.setOnClickListener(new EventOnClickListener(mApplication, this.getRef(position).getKey())); // Set the listener to our custom on click listener class that takes a key.
            }
        };
        recycler.setAdapter(mAdapter);

        // Detect if the user is offline and display a badge.
        final ImageView offlineBadge = (ImageView) findViewById(R.id.image_offline);
        final Toast offlineToast = Toast.makeText(getBaseContext(), getString(R.string.entry_offline_description), Toast.LENGTH_LONG); // Make a toast to be shown when we go offline
        offlineToast.setGravity(Gravity.BOTTOM, 0, 8); // Give the toast gravity so that it comes from the bottom of the view
        DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected"); // This Firebase reference exposes connection status to the Database
        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                boolean connected = snapshot.getValue(Boolean.class);
                if (connected) {
                    offlineBadge.setVisibility(View.INVISIBLE);
                    offlineToast.cancel(); // Cancel the offline toast if one is currently shown
                } else {
                    offlineBadge.setVisibility(View.VISIBLE);
                    offlineToast.show(); // Show the offline toast
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                FirebaseCrash.log("Listener was cancelled");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_entry, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {// If the user selects the "Settings" button then add an intent to the stack to display the AboutActivity
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        } else if (id == R.id.action_about) { // If the user selects the "About" button then add an intent to the stack to display the AboutActivity
            startActivity(new Intent(this, AboutActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

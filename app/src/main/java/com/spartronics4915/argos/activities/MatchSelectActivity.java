package com.spartronics4915.argos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.FirebaseDatabase;

import com.spartronics4915.argos.Argos;
import com.spartronics4915.argos.match.Match;
import com.spartronics4915.argos.match.MatchHolder;
import com.spartronics4915.argos.match.MatchOnClickListener;
import com.spartronics4915.argos.R;

public class MatchSelectActivity extends AppCompatActivity {
    private FirebaseDatabase mDatabase;
    private Argos mApplication;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Put top-level objects into member variables
        mApplication = ((Argos) this.getApplication()); // Get the top-level class for sharing data
        mDatabase = mApplication.getDatabase();

        setContentView(R.layout.activity_match_select); // Set the content in the activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); // Get the toolbar
        setSupportActionBar(toolbar); // Set the toolbar

        RecyclerView recycler = (RecyclerView) findViewById(R.id.recycler_matches);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new FirebaseRecyclerAdapter<Match, MatchHolder>(Match.class, android.R.layout.simple_list_item_2, MatchHolder.class, FirebaseDatabase.getInstance().getReference("gamedata").child("matchmeta")) {
            @Override
            public void populateViewHolder(MatchHolder matchViewHolder, Match match, int position) {
                matchViewHolder.setName(match.getName());
                matchViewHolder.setText(match.getStartDate());
                matchViewHolder.itemView.setOnClickListener(new MatchOnClickListener(mApplication, this.getRef(position).getKey())); // Set the listener to our custom on click listener class that takes a key.
                Log.println(Log.DEBUG, "MatchSelect", "Match added: "+match.getName());
            }
        };
        recycler.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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

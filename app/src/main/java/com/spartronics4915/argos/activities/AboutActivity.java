package com.spartronics4915.argos.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.spartronics4915.argos.BuildConfig;
import com.spartronics4915.argos.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); // Must be loaded and set *after* setContentView
        setSupportActionBar(toolbar);
        TextView infoText = (TextView) findViewById(R.id.text_about);
        infoText.append("\n\n Version "+BuildConfig.VERSION_NAME+"-"+ BuildConfig.BUILD_TYPE);

        // If we go up we should display the MatchSelectActivity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}

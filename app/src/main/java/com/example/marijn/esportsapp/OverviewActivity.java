package com.example.marijn.esportsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class OverviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
    }

    // On button click, show more matches
    public void onMoreMatchesClicked(View view) {
        startActivity(new Intent(this, StreamsActivity.class));
    }

    // On button click, show more streams
    public void onMoreStreamsClicked(View view) {
        startActivity(new Intent(this, MatchesActivity.class));
    }

}

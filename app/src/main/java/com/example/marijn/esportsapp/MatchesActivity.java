package com.example.marijn.esportsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MatchesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);

        ArrayList<MatchesInformation> arrayOfMatches = new ArrayList<>();

        // Instantiate the adapter
        MatchesAdapter matchAdapter = new MatchesAdapter(this, arrayOfMatches);

        // Get list view ID and attach the adapter to it
        ListView matchList = findViewById(R.id.matchList);
        matchList.setAdapter(matchAdapter);
    }
}

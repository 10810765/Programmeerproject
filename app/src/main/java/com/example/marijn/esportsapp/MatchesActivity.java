package com.example.marijn.esportsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MatchesActivity extends AppCompatActivity implements MatchesRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);

        // Make a request for the menu categories
        MatchesRequest matchRequest = new MatchesRequest(this);
        matchRequest.getMatches(this, 20);
    }

    @Override // Method that handles a successful call to the API
    public void gotMatches(ArrayList<MatchesInformation> matchInf) {

        // Instantiate the adapter
        MatchesAdapter matchAdapter = new MatchesAdapter(this, R.layout.match_row, matchInf);

        // Get list view ID and attach the adapter to it
        ListView matchList = findViewById(R.id.matchList);
        matchList.setAdapter(matchAdapter);
    }

    @Override // Method that handles an unsuccessful to the the API
    public void gotMatchesError(String message) {
        // Toast the error message to the screen
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}

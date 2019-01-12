package com.example.marijn.esportsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class OverviewActivity extends AppCompatActivity implements MatchesRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        // Make a request for the menu categories
        MatchesRequest request = new MatchesRequest(this);
        request.getMatches(this);
    }

    @Override // Method that handles a successful call to the API
    public void gotMatches(MatchesInformation matchInf) {

        TextView date= findViewById(R.id.dateView);
        TextView title = findViewById(R.id.titleView);
        TextView url = findViewById(R.id.urlView);

        date.setText(matchInf.getDate());
        title.setText(matchInf.getTitle());
        url.setText(matchInf.getEventUrl());

    }

    @Override // Method that handles an unsuccessful to the the API
    public void gotMatchesError(String message) {
        // Toast the error message to the screen
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        Log.d("error", message);
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

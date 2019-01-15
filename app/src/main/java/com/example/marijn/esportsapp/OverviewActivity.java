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

public class OverviewActivity extends AppCompatActivity implements MatchesRequest.Callback, StreamsRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        // Make a request for the upcoming match
        MatchesRequest matchRequest = new MatchesRequest(this);
        matchRequest.getMatches(this, 1);

        // Make a request for the most watched streamer
        StreamsRequest streamRequest = new StreamsRequest(this);
        streamRequest.getStreams(this, 1);
    }

    @Override // Method that handles a successful call to the API
    public void gotMatches(ArrayList<MatchesInformation> matchInf) {

        TextView date= findViewById(R.id.dateView);
        TextView title = findViewById(R.id.titleView);
        TextView url = findViewById(R.id.urlView);

        date.setText(matchInf.get(0).getDate());
        title.setText(matchInf.get(0).getTitle());
        url.setText(matchInf.get(0).getEventUrl());

    }

    @Override // Method that handles an unsuccessful to the the API
    public void gotMatchesError(String message) {
        // Toast the error message to the screen
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        Log.d("error", message);
    }

    @Override // Method that handles a successful call to the API
    public void gotStreams(ArrayList<StreamsInformation> streamInf) {

        TextView title= findViewById(R.id.streamTitle);
        TextView name = findViewById(R.id.streamerName);
        TextView views = findViewById(R.id.viewerCount);

        title.setText(streamInf.get(0).getTitle());
        name.setText(streamInf.get(0).getName());
        views.setText(streamInf.get(0).getViewers());
    }

    @Override // Method that handles an unsuccessful to the the API
    public void gotStreamsError(String message) {
        // Toast the error message to the screen
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        Log.d("error", message);
    }
    // On button click, show more matches
    public void onMoreMatchesClicked(View view) {
        startActivity(new Intent(this, MatchesActivity.class));
    }

    // On button click, show more streams
    public void onMoreStreamsClicked(View view) {
        startActivity(new Intent(this, StreamsActivity.class));
    }

}

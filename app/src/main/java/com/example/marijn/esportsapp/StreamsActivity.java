package com.example.marijn.esportsapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class StreamsActivity extends AppCompatActivity implements StreamsRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_streams);

        // Make a request for the top 20 streams
        StreamsRequest streamRequest = new StreamsRequest(this);
        streamRequest.getStreams(this, 20);

        BottomNavigationView navBar = findViewById(R.id.navigation);
        navBar.setOnNavigationItemSelectedListener(navListener);
        navBar.getMenu().findItem(R.id.navigate_stream).setChecked(true);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    BottomNavigationView navBar = findViewById(R.id.navigation);
                    navBar.getMenu().findItem(item.getItemId()).setChecked(true);

                    switch (item.getItemId()) {
                        case R.id.navigate_home:
                            Intent home = new Intent(getApplicationContext(), OverviewActivity.class);
                            startActivity(home);
                            break;
                        case R.id.navigate_match:
                            Intent match = new Intent(getApplicationContext(), MatchesActivity.class);
                            startActivity(match);
                            break;
                        case R.id.navigate_stream:
                            Intent stream = new Intent(getApplicationContext(), StreamsActivity.class);
                            startActivity(stream);
                            break;
                        case R.id.navigate_refresh:
                            break;
                    }

                    return true;
                }
            };

    @Override // Method that handles a successful call to the API
    public void gotStreams(ArrayList<StreamsInformation> streamInf) {

        // Instantiate the adapter
        StreamsAdapter streamAdapter = new StreamsAdapter(this, R.layout.stream_row, streamInf);

        // Get list view ID and attach the adapter to it
        ListView streamList = findViewById(R.id.streamList);
        streamList.setAdapter(streamAdapter);
    }

    @Override // Method that handles an unsuccessful to the the API
    public void gotStreamsError(String message) {
        // Toast the error message to the screen
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}

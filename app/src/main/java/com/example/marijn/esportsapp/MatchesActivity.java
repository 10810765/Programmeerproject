package com.example.marijn.esportsapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MatchesActivity extends AppCompatActivity implements MatchesRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);

        Spinner mySpinner = findViewById(R.id.gameSpinner);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.games));

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        BottomNavigationView navBar = findViewById(R.id.navigation);
        navBar.setOnNavigationItemSelectedListener(navListener);
        navBar.getMenu().findItem(R.id.navigate_match).setChecked(true);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();

                // Make a request for the top 20 streams
                MatchesRequest matchRequest = new MatchesRequest(MatchesActivity.this);

                switch (selectedItem) {
                    case "League of Legends":
                        matchRequest.getMatches(MatchesActivity.this, "lol", 20);
                        break;
                    case "Overwatch":
                        matchRequest.getMatches(MatchesActivity.this, "ow", 20);
                        break;
                    case "Dota 2":
                        matchRequest.getMatches(MatchesActivity.this, "dota2", 20);
                        break;
                    case "Counter-Strike: Global Offensive":
                        matchRequest.getMatches(MatchesActivity.this, "csgo", 20);
                        break;
                }

            } // to close the onItemSelected

            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

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

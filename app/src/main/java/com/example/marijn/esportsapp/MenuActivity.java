package com.example.marijn.esportsapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Marijn Meijering <m.h.j.meijering@uva.nl>
 * 10810765 Universiteit van Amsterdam
 * Minor Programmeren 01/02/2019
 */
public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Set an on navigation item selected listener for the navigation menu
        BottomNavigationView navMenu = findViewById(R.id.navigationMenu);
        navMenu.setOnNavigationItemSelectedListener(new OnNavigationItemSelectedListener());

        // If there is no previously selected fragment, open FeaturedFragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                    new FeaturedFragment()).commit();
        }
    }

    // Create an on navigation fragment selected listener (bottom navigation menu)
    // With help from: https://www.youtube.com/watch?v=tPV8xA7m-iw&t=103s
    private class OnNavigationItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            // Decide which fragment has to be opened (based on navigation menu click)
            switch (item.getItemId()) {
                case R.id.navigate_home:
                    selectedFragment = new FeaturedFragment();
                    break;
                case R.id.navigate_match:
                    selectedFragment = new MatchesFragment();
                    break;
                case R.id.navigate_stream:
                    selectedFragment = new StreamsFragment();
                    break;
                case R.id.navigate_favourite:
                    selectedFragment = new FavouritesFragment();
                    break;
            }

            // Replace the current fragment with the (new) clicked fragment
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                    selectedFragment).commit();

            return true;
        }
    }
}

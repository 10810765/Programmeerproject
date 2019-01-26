package com.example.marijn.esportsapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        BottomNavigationView navBar = findViewById(R.id.navigationBar);
        navBar.setOnNavigationItemSelectedListener(new OnNavigationItemSelectedListener());

        // If there is no previously saved fragment, open FeaturedFragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                    new FeaturedFragment()).commit();
        }
    }

    // Create an on navigation fragment selected listener (bottom menu bar)
    // With help from: https://www.youtube.com/watch?v=tPV8xA7m-iw&t=103s
    private class OnNavigationItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

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

            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                    selectedFragment).commit();

            return true;
        }
    }
}

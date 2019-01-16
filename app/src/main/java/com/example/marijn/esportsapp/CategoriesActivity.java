package com.example.marijn.esportsapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class CategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        BottomNavigationView navBar = findViewById(R.id.navigation);
        navBar.setOnNavigationItemSelectedListener(navListener);
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

    // On button click, go to Overview Activity
    public void onCategoryClicked(View view){
        Button pressedGame = findViewById(view.getId());
        String categoryId = String.valueOf(pressedGame.getTag());

        // Pass the chosen category to the next activity
        Intent intent = new Intent(this, OverviewActivity.class);
        intent.putExtra("category_id", categoryId);
        startActivity(intent);
    }
}
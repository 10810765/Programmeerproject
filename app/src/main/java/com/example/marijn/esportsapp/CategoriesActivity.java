package com.example.marijn.esportsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
    }

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
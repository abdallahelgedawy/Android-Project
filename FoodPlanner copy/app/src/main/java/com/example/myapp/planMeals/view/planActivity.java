package com.example.myapp.planMeals.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.myapp.R;
import com.example.myapp.dailyMeal.view.DailyMealActivity;
import com.example.myapp.favorite.view.Favorite_itemsActivity;
import com.example.myapp.search.view.SearchActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class planActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), DailyMealActivity.class));
                        return true;
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                        return true;
                    case R.id.saved:
                        startActivity(new Intent(getApplicationContext(), Favorite_itemsActivity.class));
                        return true;
                    case R.id.planed:
                        startActivity(new Intent(getApplicationContext(), com.example.myapp.planMeals.view.planActivity.class));
                        return true;
                }
                return false;
            }
        });

    }
}
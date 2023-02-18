package com.example.myapp.detailedmeal.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.myapp.R;
import com.example.myapp.dailyMeal.view.DailyMealActivity;
import com.example.myapp.login.view.LoginActivity;
import com.example.myapp.search.category.view.CategoryActivity;
import com.example.myapp.search.category.view.CategoryMealsActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DetailedMealActivity extends AppCompatActivity {
    ImageView arrow;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_meal);
        arrow = findViewById(R.id.arrow);
        firebaseAuth = FirebaseAuth.getInstance();

        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailedMealActivity.this, DailyMealActivity.class);
                startActivity(intent);
            }
        });
    }

  }
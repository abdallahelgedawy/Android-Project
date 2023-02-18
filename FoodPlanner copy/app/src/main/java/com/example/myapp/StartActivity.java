package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.dailyMeal.view.DailyMealActivity;
import com.example.myapp.search.byIngredients.view.IngredientsActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class StartActivity extends AppCompatActivity {
    Button button;
    FirebaseAuth firebaseAuth;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.start);

        button = findViewById(R.id.start_btn);
        firebaseAuth = FirebaseAuth.getInstance();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, WelcomActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


 private  void checkUserStatus(){
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if(user != null){
                Intent intent = new Intent(getApplicationContext(), DailyMealActivity.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(getApplicationContext(), WelcomActivity.class);
                startActivity(intent);
                finish();
            }
        }

    }


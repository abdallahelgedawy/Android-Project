package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;
import com.example.myapp.dailyMeal.view.DailyMealActivity;
import com.example.myapp.login.view.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreenActivity extends AppCompatActivity {
    LottieAnimationView lottieAnimationView;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        lottieAnimationView = findViewById(R.id.lottie);

        firebaseAuth = FirebaseAuth.getInstance();

        lottieAnimationView.animate().translationX(20000).setDuration(2300).setStartDelay(4000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
             checkUserStatus();
             finish();
            }
        },4000);
    }

    private  void checkUserStatus(){
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null){
            Intent intent = new Intent(getApplicationContext(), DailyMealActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(getApplicationContext(), StartActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
package com.example.myapp.registration.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapp.R;

import com.example.myapp.dailyMeal.view.DailyMealActivity;
import com.example.myapp.login.view.LoginActivity;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }
    public void login(View view) {
        startActivities(new Intent[]{new Intent(RegistrationActivity.this, LoginActivity.class)});
    }

    public void actiivitymain(View view) {
        startActivities(new Intent[]{new Intent(RegistrationActivity.this, LoginActivity.class)});
    }
}
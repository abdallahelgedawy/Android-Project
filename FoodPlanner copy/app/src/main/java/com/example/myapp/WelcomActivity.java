package com.example.myapp;

import androidx.activity.result.ActivityResultCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.myapp.login.view.LoginActivity;
import com.example.myapp.registration.view.RegistrationActivity;

public class WelcomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcom);
    }

    public void register(View view) {
        startActivities(new Intent[]{new Intent(WelcomActivity.this, RegistrationActivity.class)});
    }

    public void login(View view) {
        startActivities(new Intent[]{new Intent(WelcomActivity.this, LoginActivity.class)});


    }
}
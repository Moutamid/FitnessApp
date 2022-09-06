package com.example.fitnessapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.view.View;

public class SplashScreen extends AppCompatActivity {

    private static int SSPLASH_TIME = 5000;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mAuth = FirebaseAuth.getInstance();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (mAuth.getCurrentUser() != null){
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }else {
                    startActivity(new Intent(getApplicationContext(), Login.class));
                    finish();
                }
            }
        },SSPLASH_TIME);
    }

}

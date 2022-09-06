package com.example.fitnessapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.example.fitnessapp.Services.AlarmReceiver;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private int rId;
    private SharedPreferencesManager prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = new SharedPreferencesManager(this);
        rId = getIntent().getIntExtra("rid",0);
        if (rId != 0){
            disableAlarm(rId);
        }
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNav);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_fragment,new HomeScreen()).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_fragment,new HomeScreen()).commit();
                        break;
                    case R.id.action_exercise:
                        Intent intent = new Intent(MainActivity.this,ExcerciseScreen.class);
                        startActivity(intent);
                        break;
                    case R.id.action_nutrition:
                        Intent intent2 = new Intent(MainActivity.this,FitnessNutrition.class);
                        startActivity(intent2);
                        break;
                    case R.id.action_settings:
                        Intent intent1 = new Intent(MainActivity.this,PersonalSettings.class);
                        startActivity(intent1);
                        break;
                }
                return true;
            }
        });
    }

    //Disable Notification for next 2 hours
    private void disableAlarm(int rId) {
        prefs.storeString("repeat","true");
        prefs.storeString("active","true");
        NotificationManagerCompat.from(this).cancel(rId);
        //NotificationManagerCompat.from(this).cancelAll();
        //new AlarmReceiver().cancelAlarm(this,rId);
    }
}

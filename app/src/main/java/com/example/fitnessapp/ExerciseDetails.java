package com.example.fitnessapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ExerciseDetails extends AppCompatActivity {

    private ImageView back,logo;
    private TextView description,titleTxt;
    private String details,title;
    private int image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_details);
        back = (ImageView)findViewById(R.id.back);
        logo = (ImageView)findViewById(R.id.image);
        description = (TextView) findViewById(R.id.description);
        titleTxt = (TextView) findViewById(R.id.title);
        title = getIntent().getStringExtra("title");
        details = getIntent().getStringExtra("descp");
        image = getIntent().getIntExtra("logo",0);

        titleTxt.setText(title);
        description.setText(details);
        logo.setImageResource(image);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ExcerciseScreen.class));
            }
        });
    }

}

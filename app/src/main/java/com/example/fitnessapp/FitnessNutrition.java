package com.example.fitnessapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class FitnessNutrition extends AppCompatActivity {

    LinearLayout bmiLayout,caloriesLayout,recipeLayout,water_intake_layout;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness_nutrition);
        back = (ImageView)findViewById(R.id.back);
        bmiLayout = (LinearLayout) findViewById(R.id.bmi);
        caloriesLayout = (LinearLayout) findViewById(R.id.calories);
        recipeLayout = (LinearLayout) findViewById(R.id.recipes);
        water_intake_layout = (LinearLayout) findViewById(R.id.water_intake);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        bmiLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),BMICalculator.class));
            }
        });
        caloriesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),CaloriesCalculator.class));
            }
        });
        water_intake_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),WaterIntakeCalculator.class));
            }
        });
        recipeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),FoodRecipes.class));
            }
        });
    }
}
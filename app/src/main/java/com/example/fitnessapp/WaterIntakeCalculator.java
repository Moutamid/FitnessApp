package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class WaterIntakeCalculator extends AppCompatActivity {

    private EditText ageInput;
    private Button calculateBtn;
    private ImageView back;
    private SharedPreferencesManager prefs;
    private String age;
    private ProgressDialog pd;
    RadioButton selectedRadioButton;
    int selectedRadioId;
    RadioGroup radioGroup1;
    private String gender;
    private LinearLayout intake_layout;
    private TextView intakeTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_intake_calculator);
        back = (ImageView)findViewById(R.id.back);
        prefs = new SharedPreferencesManager(this);
        ageInput = (EditText)findViewById(R.id.age);
        radioGroup1 = (RadioGroup)findViewById(R.id.radioGroup1);
        calculateBtn = (Button)findViewById(R.id.signinBtn);
        intake_layout = (LinearLayout) findViewById(R.id.intake_layout);
        intakeTxt = (TextView) findViewById(R.id.intake);
        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateBtn.setClickable(false);
                if(validInfo()) {
                    calculateBtn.setClickable(true);
                    pd = new ProgressDialog(WaterIntakeCalculator.this);
                    pd.setMessage("Saving Information....");
                    pd.show();
                    saveDetails();

                }
                else{
                    calculateBtn.setClickable(true);
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), FitnessNutrition.class));
            }
        });
    }

    private void saveDetails() {
        selectedRadioId = radioGroup1.getCheckedRadioButtonId();
        if (selectedRadioId != -1) {
            selectedRadioButton = (RadioButton) findViewById(selectedRadioId);
            //  radioBtn1 = selectedRadioButton.getText().toString();
        }
        gender = selectedRadioButton.getText().toString();
        prefs.storeString("gender", gender);
        prefs.storeInt("age", Integer.parseInt(age));
        calculateIntake(Integer.parseInt(age),gender);
        pd.dismiss();
    }
    //Calculate Water Intake
    private void calculateIntake(int age, String gender) {

        intake_layout.setVisibility(View.VISIBLE);
        if (age > 3 && age < 9){
            intakeTxt.setText("5 cups, or 40 oz per day");
        }
        if (age > 8 && age < 14){
            intakeTxt.setText("7–8 cups, or 56–64 oz per day");
        }
        if (age > 13 && age < 19){
            intakeTxt.setText("8–11 cups, or 64–88 oz per day");
        }
        if (gender.equals("Male") && age > 18){
            intakeTxt.setText("13 cups, or 104 oz per day");
        }
        if (gender.equals("Female") && age > 18){
            intakeTxt.setText("women 9 cups, or 72 oz per day");
        }
    }


    public boolean validInfo() {

        age = ageInput.getText().toString();
        if (age.isEmpty()) {
            ageInput.setError("Input Age!");
            ageInput.requestFocus();
            return false;
        }
        return true;
    }

}
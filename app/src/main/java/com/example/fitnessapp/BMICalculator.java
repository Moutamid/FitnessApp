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
import android.widget.Toast;

public class BMICalculator extends AppCompatActivity {

    private EditText ageInput,feetInput,incheInput,weightInput;
    private Button calculateBtn;
    private ImageView back;
    private SharedPreferencesManager prefs;
    private String age,feet,inches,weight;
    private ProgressDialog pd;
    RadioButton selectedRadioButton;
    int selectedRadioId;
    float intheight,intweight;
    RadioGroup radioGroup1;
    private String gender;
    float intbmi;
    private LinearLayout bmi_layout;
    private TextView bmiTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalculator);
        back = (ImageView)findViewById(R.id.back);
        prefs = new SharedPreferencesManager(this);
        ageInput = (EditText)findViewById(R.id.age);
        radioGroup1 = (RadioGroup)findViewById(R.id.radioGroup1);
        feetInput = (EditText)findViewById(R.id.feet);
        incheInput = (EditText)findViewById(R.id.inches);
        weightInput = (EditText)findViewById(R.id.weight);
        calculateBtn = (Button)findViewById(R.id.signinBtn);
        bmi_layout = (LinearLayout) findViewById(R.id.bmi_layout);
        bmiTxt = (TextView) findViewById(R.id.bmi);
        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateBtn.setClickable(false);
                if(validInfo()) {
                    calculateBtn.setClickable(true);
                    pd = new ProgressDialog(BMICalculator.this);
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
        prefs.storeInt("feet", Integer.parseInt(feet));
        prefs.storeInt("inches", Integer.parseInt(inches));
        prefs.storeInt("weight", Integer.parseInt(weight));
        calculateHeight(Integer.parseInt(feet),Integer.parseInt(inches),Integer.parseInt(weight),gender);
        pd.dismiss();
    }

    //Calculate BMI For Men And Women
    private void calculateHeight(int f, int i, int w, String g) {
        int feets = f * 12;
        int inche = feets + i;
        //convert height in feets inches to centimeter
        double height =  inche * 2.54;
        prefs.storeInt("height", (int) height);
        intheight = (float) height;
        intweight = (float) w;

        intheight = intheight / 100;
        intbmi = intweight / (intheight * intheight);

        float bmi = Math.abs(intbmi);
        bmi_layout.setVisibility(View.VISIBLE);
        if (g.equals("Male")){
            bmiTxt.setText(String.valueOf(bmi));
        }else if (g.equals("Female")){
            float femaleBmi = (float) (intbmi - (1.905));
            float bmiTotal = Math.abs(femaleBmi);
            bmiTxt.setText(String.valueOf(bmiTotal));
        }

    }


    public boolean validInfo() {

        age = ageInput.getText().toString();
        feet = feetInput.getText().toString();
        inches = incheInput.getText().toString();
        weight = weightInput.getText().toString();


        if (age.isEmpty()) {
            ageInput.setError("Input Age!");
            ageInput.requestFocus();
            return false;
        }
        if (feet.isEmpty()) {
            feetInput.setError("Input Height!");
            feetInput.requestFocus();
            return false;
        }
        if (inches.isEmpty()) {
            incheInput.setError("Input Height!");
            incheInput.requestFocus();
            return false;
        }
        if (weight.isEmpty()) {
            weightInput.setError("Input Weight!");
            weightInput.requestFocus();
            return false;
        }
        return true;
    }

}
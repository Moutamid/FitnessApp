package com.example.fitnessapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class PersonalDetails extends AppCompatActivity {


    private EditText fnameInput,ageInput,feetInput,incheInput,weightInput;
    private Button saveBtn;
    private ImageView back;
    private String fullname,age,feet,inches,weight;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    private ProgressDialog pd;
    private SharedPreferencesManager prefs;
    RadioButton selectedRadioButton;
    int selectedRadioId;
    RadioGroup radioGroup1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);
        back = (ImageView)findViewById(R.id.back);
        prefs = new SharedPreferencesManager(this);
        fnameInput = (EditText)findViewById(R.id.fullname);
        radioGroup1 = (RadioGroup)findViewById(R.id.radioGroup1);
        ageInput = (EditText)findViewById(R.id.age);
        feetInput = (EditText)findViewById(R.id.feet);
        incheInput = (EditText)findViewById(R.id.inches);
        weightInput = (EditText)findViewById(R.id.weight);
        saveBtn = (Button)findViewById(R.id.signinBtn);
        mAuth = FirebaseAuth.getInstance();
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveBtn.setClickable(false);
                if(validInfo()) {
                    saveBtn.setClickable(true);
                    pd = new ProgressDialog(PersonalDetails.this);
                    pd.setMessage("Saving Information....");
                    pd.show();
                    saveDetails();

                }
                else{
                    saveBtn.setClickable(true);
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });
    }

    //Convert height in feet inches to centimeters
    private void calculateHeight() {
        int feets = Integer.parseInt(feet) * 12;
        int inche = feets + Integer.parseInt(inches);
        double height =  inche * 2.54;
        prefs.storeInt("height", (int) height);
    }

    //Save User Details
    private void saveDetails() {
        selectedRadioId = radioGroup1.getCheckedRadioButtonId();
        if (selectedRadioId != -1) {
            selectedRadioButton = (RadioButton) findViewById(selectedRadioId);
            //  radioBtn1 = selectedRadioButton.getText().toString();
        }
        prefs.storeString("fullname", fullname);
        prefs.storeString("gender", selectedRadioButton.getText().toString());
        prefs.storeInt("age", Integer.parseInt(age));
        prefs.storeInt("feet", Integer.parseInt(feet));
        prefs.storeInt("inches", Integer.parseInt(inches));
        prefs.storeInt("weight", Integer.parseInt(weight));
        calculateHeight();
        pd.dismiss();
        sendActivityToMainDashboard();
    }

    private void sendActivityToMainDashboard() {
        Intent intent = new Intent(PersonalDetails.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    //Validate User Details
    public boolean validInfo() {
        //Setting params of user
        fullname = fnameInput.getText().toString();
        age = ageInput.getText().toString();
        feet = feetInput.getText().toString();
        inches = incheInput.getText().toString();
        weight = weightInput.getText().toString();

        if (fullname.isEmpty()) {
            fnameInput.setError("Input Fullname!");
            fnameInput.requestFocus();
            return false;
        }
        return true;
    }
}

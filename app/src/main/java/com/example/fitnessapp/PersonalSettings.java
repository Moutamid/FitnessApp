package com.example.fitnessapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class PersonalSettings extends AppCompatActivity {

    private EditText fnameInput,ageInput,feetInput,incheInput,weightInput;
    private Button saveBtn;
    private ImageView back;
    private TextView email;
    private String fullname,emailAddr,gender;
    private int age,feet,inches,weight;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    private ProgressDialog pd;
    private SharedPreferencesManager prefs;
    RadioButton maleBtn,femaleBtn;
    int selectedRadioId;
    RadioGroup radioGroup1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_settings);
        back = (ImageView)findViewById(R.id.back);
        prefs = new SharedPreferencesManager(this);
        fnameInput = (EditText)findViewById(R.id.fullname);
        ageInput = (EditText)findViewById(R.id.age);
        feetInput = (EditText)findViewById(R.id.feet);
        incheInput = (EditText)findViewById(R.id.inches);
        weightInput = (EditText)findViewById(R.id.weight);
        //username = (TextView) findViewById(R.id.username);
        email = (TextView) findViewById(R.id.emailAddr);
        radioGroup1 = (RadioGroup)findViewById(R.id.radioGroup1);
        maleBtn = (RadioButton) findViewById(R.id.male);
        femaleBtn = (RadioButton) findViewById(R.id.female);
        saveBtn = (Button)findViewById(R.id.signinBtn);
        mAuth = FirebaseAuth.getInstance();
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAuth.getCurrentUser() != null) {
                    saveBtn.setClickable(true);
                    pd = new ProgressDialog(PersonalSettings.this);
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
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (maleBtn.isChecked()){
                    prefs.storeString("gender", "Male");
                }else if (femaleBtn.isChecked()){
                    prefs.storeString("gender", "Female");
                }
            }
        });
        checkDataExists();
    }

    private void saveDetails() {
        prefs.storeString("fullname", fnameInput.getText().toString());
        prefs.storeInt("age", Integer.parseInt(ageInput.getText().toString()));
        prefs.storeInt("feet", Integer.parseInt(feetInput.getText().toString()));
        prefs.storeInt("inches", Integer.parseInt(incheInput.getText().toString()));
        prefs.storeInt("weight", Integer.parseInt(weightInput.getText().toString()));
        pd.dismiss();
        calculateHeight();
        sendActivityToMainDashboard();
    }
    //Convert height in feet inches to centimeters
    private void calculateHeight() {
        int feets = Integer.parseInt(feetInput.getText().toString()) * 12;
        int inche = feets + Integer.parseInt(incheInput.getText().toString());
        double height =  inche * 2.54;
        prefs.storeInt("height", (int) height);
    }
    private void sendActivityToMainDashboard() {
        Intent intent = new Intent(PersonalSettings.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    //Check user detiails exists
    private void checkDataExists() {
        fullname = prefs.retrieveString("fullname","");
        gender = prefs.retrieveString("gender","");
        age = prefs.retrieveInt("age",0);
        feet = prefs.retrieveInt("feet",0);
        inches = prefs.retrieveInt("inches",0);
        weight = prefs.retrieveInt("weight",0);
        fnameInput.setText(fullname);
        ageInput.setText(String.valueOf(age));
        feetInput.setText(String.valueOf(feet));
        incheInput.setText(String.valueOf(inches));
        weightInput.setText(String.valueOf(weight));
        email.setText(mAuth.getCurrentUser().getEmail());
        if (gender.equals("Male")){
            maleBtn.setChecked(true);
        }else if(gender.equals("Female")){
            femaleBtn.setChecked(true);
        }
    }

}

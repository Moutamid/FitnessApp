package com.example.fitnessapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText emailInput,passInput;
    private Button loginBtn,signUpBtn;
    private FirebaseAuth mAuth;
    private String email,password;
    private ProgressDialog pd;
    private FirebaseUser firebaseUser;
    private SharedPreferencesManager prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailInput = (EditText)findViewById(R.id.emailAddr);
        passInput = (EditText)findViewById(R.id.signinPassword);
        loginBtn = (Button)findViewById(R.id.signinBtn);
        signUpBtn = (Button)findViewById(R.id.create);
        prefs = new SharedPreferencesManager(this);
        mAuth = FirebaseAuth.getInstance();
        // Staying signed in
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginBtn.setClickable(false);
                if(validEmailAndPassword()) {
                    loginBtn.setClickable(true);
                    pd = new ProgressDialog(Login.this);
                    pd.setMessage("Loading");
                    pd.show();
                    loginUser(email,password);
                }
                else{
                    loginBtn.setClickable(true);
                }
            }
        });
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SignUp.class));
            }
        });

    }

    //Login
    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            pd.dismiss();
                            FirebaseUser currrentUser = mAuth.getCurrentUser();
                            finish();
                            startActivity(new Intent(getApplicationContext(),
                                    MainActivity.class));
                            //    Common.currentPhone = currrentUser.getPhoneNumber();
                            Toast.makeText(Login.this, "Logged In",
                                    Toast.LENGTH_SHORT).show();
                            // overridePendingTransition(R.anim.slide_in_left,R.anim.slide_in_right);
                        } else {
                            pd.dismiss();
                            Toast.makeText(Login.this, "couldn't login",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                e.printStackTrace();
            }
        });

    }

    //Validate Email And Password
    public boolean validEmailAndPassword() {
        email = emailInput.getText().toString();
        password = passInput.getText().toString();

        if (email.isEmpty()) {
            emailInput.setError("Input Email!");
            emailInput.requestFocus();
            return false;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailInput.setError("Please input valid email!");
            emailInput.requestFocus();
            return false;
        }
        if (password.isEmpty()) {
            passInput.setError("Input password!");
            passInput.requestFocus();
            return false;

        }
        return true;
    }

}


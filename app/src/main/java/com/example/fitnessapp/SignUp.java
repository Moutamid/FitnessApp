package com.example.fitnessapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.tasks.OnCompleteListener;
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
import android.widget.ImageView;
import android.widget.TextView;

public class SignUp extends AppCompatActivity {

    private EditText emailInput,passInput;
    private Button loginBtn;
    private TextView signIn;
    private ImageView back;
    private String email,password;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    private ProgressDialog pd;
    private SharedPreferencesManager prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        back = (ImageView)findViewById(R.id.back);
        prefs = new SharedPreferencesManager(this);
        emailInput = (EditText)findViewById(R.id.emailAddr);
        passInput = (EditText)findViewById(R.id.signinPassword);
        //usernameInput = (EditText)findViewById(R.id.signinUsername);
        //  cpassInput = (EditText)findViewById(R.id.cPassword);
        loginBtn = (Button)findViewById(R.id.signinBtn);
        signIn = (TextView) findViewById(R.id.signIn);
        //    checkBox = (CheckBox)findViewById(R.id.agreed);
        mAuth = FirebaseAuth.getInstance();
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginBtn.setClickable(false);
                if(validInfo()) {
                    loginBtn.setClickable(true);
                    pd = new ProgressDialog(SignUp.this);
                    pd.setMessage("Creating Account....");
                    pd.show();
                    registerUser();

                }
                else{
                    loginBtn.setClickable(true);
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

    //Register User
    private void registerUser() {
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            if (mAuth.getCurrentUser() != null){
                                firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                                prefs.storeString("id", firebaseUser.getUid());
                                prefs.storeString("email", email);
                                prefs.storeString("password", password);
                                sendActivityToMainDashboard();
                                pd.dismiss();
                            }
                        }
                    }
                });
    }

    private void sendActivityToMainDashboard() {
        Intent intent = new Intent(SignUp.this,PersonalDetails.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    //Validate Input Fields
    public boolean validInfo() {
        email = emailInput.getText().toString();
        password = passInput.getText().toString();
        if (email.isEmpty()) {
            emailInput.setError("Input email!");
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

        if (password.length() < 6) {
            passInput.setError("password must be atleast 6 character!");
            passInput.requestFocus();
            return false;
        }

        return true;
    }
}

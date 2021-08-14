package com.example.eduapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    EditText email, password;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;
    CheckBox remember;
    TextView loginText, signUpText;
    Button signupBttn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.emailg);
        password = findViewById(R.id.passwordg);
        remember = (CheckBox) findViewById(R.id.checkBoxg);
        progressBar = findViewById(R.id.progressBarg);
        loginText = findViewById(R.id.loginTv);
        signUpText = findViewById(R.id.signuptext);
        signupBttn = findViewById(R.id.signupBttn);


        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String checkbox = preferences.getString("remember", "");

        if (checkbox.equals("true")) {
          /*  Intent intent = new Intent(getApplicationContext(), dashboardg.class);
            startActivity(intent);
            finish();*/
        }
        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    SharedPreferences preferences1 = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences1.edit();
                    editor.putString("remember", "true");
                    editor.apply();
                } else if (!buttonView.isChecked()) {
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "false");
                    editor.apply();
                }
            }
        });


    }

    public void loging(View view) {

        String EmailS = email.getText().toString().toLowerCase().trim();
        String passwordS = password.getText().toString().trim();


        if (EmailS.isEmpty()) {
            this.email.setError("Field can't be empty");
            this.email.requestFocus();
            return;
        }


        if (passwordS.isEmpty()) {
            password.setError("field required");
            password.requestFocus();
            return;
        }

        if (passwordS.length() < 6) {
            password.setError("minimum length of passwword is 6 leter");
            password.requestFocus();
        }


        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(EmailS, passwordS).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    progressBar.setVisibility(View.INVISIBLE);
                  /*  Intent intent = new Intent(getApplicationContext(), dashboardg.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();*/

                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });



    }

    public void forgotPassword(View view) {
    }
}
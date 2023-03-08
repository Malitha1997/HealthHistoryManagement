package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    Button btnSignupView;
    Button btnLogin;
    EditText loginUserName;
    EditText  loginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        btnSignupView=findViewById(R.id.btnSignupView);
        btnLogin=findViewById(R.id.btnLogin);
        loginUserName=findViewById(R.id.loginUserName);
        loginPassword=findViewById(R.id.loginPassword);

        loginUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(loginUserName.getText().toString().trim().isEmpty()){
                    loginUserName.setError("Username should not be empty");
                }
                else{
                    Toast.makeText(getApplicationContext(), "Sucessful!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        loginPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(loginPassword.getText().toString().trim().isEmpty()){
                    loginPassword.setError("Password should not be empty");
                }
                else{
                    Toast.makeText(getApplicationContext(), "Sucessful!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void SignUpScreen(View view){
        Intent i=new Intent(LoginActivity.this,MainActivity.class);
        startActivity(i);
    }

    public void HomeScreen(View view){
        Intent i=new Intent(LoginActivity.this,Home.class);
        startActivity(i);
    }

    //Validation
    private boolean validateLoginUsername(){
        String val = loginUserName.getText().toString();
        if (val.isEmpty()) {
            loginUserName.setError("Field can not be empty");
            return false;
        }else{
            loginUserName.setError(null);
            return true;
        }
    }

    private boolean validateLoginPassword(){
        String val = loginPassword.getText().toString();
        if (val.isEmpty()) {
            loginPassword.setError("Field can not be empty");
            return false;
        }else{
            loginPassword.setError(null);
            return true;
        }
    }

    public void LoginScreen(View view) {
        if (validateLoginUsername() && validateLoginPassword()) {
            Intent i = new Intent(LoginActivity.this, Home.class);
            startActivity(i);
        } else {
            loginUserName.setError("Invalid Username");
            loginPassword.setError("Invalid Password");
        }
    }
}
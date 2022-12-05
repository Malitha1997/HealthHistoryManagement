package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    TextView viewSignup;
    Button btnLogin;
    EditText loginUserName;
    EditText  loginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        viewSignup=findViewById(R.id.viewSignup);
        loginUserName=findViewById(R.id.loginUserName);
        loginPassword=findViewById(R.id.loginPassword);

        viewSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Home.class);
                startActivity(i);
            }
        });

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




}
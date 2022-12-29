package com.example.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView viewLogin;
    EditText username;
    EditText email;
    EditText InputPassword;
    EditText inputConfirmPassword;
    Button button;
    private DbHandler dbHandler;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewLogin=findViewById(R.id.viewLogin);
        username=findViewById(R.id.username);
        email=findViewById(R.id.email);
        InputPassword=findViewById(R.id.InputPassword);
        inputConfirmPassword=findViewById(R.id.inputConfirmPassword);
        button=findViewById(R.id.button);
        context = this;

        dbHandler = new DbHandler(context);

        viewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);


            }
        });


        username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().trim().isEmpty()){
                    username.setError("Username should not be empty");
                }
                else{
                    Toast.makeText(getApplicationContext(), "Sucessful!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText().toString().trim().isEmpty()){
                    email.setError("Email should not be empty");
                }
                else{
                    Toast.makeText(getApplicationContext(), "Sucessful!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        InputPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(InputPassword.getText().toString().trim().isEmpty()){
                    InputPassword.setError("Password should not be empty");
                }
                else{
                    Toast.makeText(getApplicationContext(), "Sucessful!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        inputConfirmPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(inputConfirmPassword.getText().toString().trim().isEmpty()){
                    inputConfirmPassword.setError("Confirm password should not be empty");
                }
                else{
                    Toast.makeText(getApplicationContext(), "Sucessful!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String patientusername = username.getText().toString();
                String patientemail = email.getText().toString();
                String patientpassword = InputPassword.getText().toString();
                String patientconfirmpassword = inputConfirmPassword.getText().toString();
                long started = System.currentTimeMillis();

                PatientModel patientModel=new PatientModel(patientusername,patientemail,patientpassword,patientconfirmpassword,started,0);
                dbHandler.addPatient(patientModel);
            }
        });


    }


}
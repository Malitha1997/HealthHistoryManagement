package com.example.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    Button btnSignup;
    private DbHandler dbHandler;
    private Context context;
    //Intent i;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewLogin=findViewById(R.id.viewLogin);
        username=findViewById(R.id.username);
        email=findViewById(R.id.email);
        InputPassword=findViewById(R.id.InputPassword);
        inputConfirmPassword=findViewById(R.id.inputConfirmPassword);
        btnSignup=findViewById(R.id.btnSignup);
        context = this;
        //i=new Intent(MainActivity.this,LoginActivity.class);

        dbHandler = new DbHandler(context);


        viewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
            }
        });


        /*username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().trim().isEmpty()){
                    username.setError("Username should not be empty");
                }
                else if(username.length()>=5){
                    Toast.makeText(getApplicationContext(), "Username is too long", Toast.LENGTH_SHORT).show();
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

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               try{
                   String patientusername = username.getText().toString();
                   String patientemail = email.getText().toString();
                   String patientpassword = InputPassword.getText().toString();
                   String patientconfirmpassword = inputConfirmPassword.getText().toString();
                   long started = System.currentTimeMillis();

                   PatientModel patientModel=new PatientModel(patientusername,patientemail,patientpassword,patientconfirmpassword,started,0);
                   dbHandler.addPatient(patientModel);
                   Log.i("xxxxxxxxxxxxxxxxx","3333333333333333");
                   startActivity(i); Log.i("xxxxxxxxxxxxxxxxx","4444444444444444");
               }catch(Exception e){
                   Log.i("xxxxxxxxxxxxxxxxx","111111111111");
                   e.printStackTrace();
               }
            }
        });*/
    }

    //Signup Validation
    private Boolean validateUsername() {
        String val = username.getText().toString();
        String noWhiteSpace = "(?=\\S+$)";
        if (val.isEmpty()) {
            username.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            username.setError("Username is too long");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            username.setError("White spaces are not allowed");
            return false;
        } else {
            username.setError(null);
            return true;
        }
    }
    private Boolean validateEmail() {

        String val = email.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            email.setError("Field can not be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            email.setError("Invalid Email Address");
            return false;
        } else {
            email.setError(null);
            return true;
        }

    }

    private Boolean validatePassword() {

        String val = InputPassword.getText().toString();
        String val2 = inputConfirmPassword.getText().toString();
        String passwordVal = "^" +
                "(?=.*[a-zA-Z])" + // any letter
                "(?=.*[@#&%$^+=])" + // at least 1 special character
                "(?=\\s+S)" + // no white space
                ".{8,}";// at least 8 character

        if (val.isEmpty()) {
            InputPassword.setError("Field can not be empty");
            return false;
        } else if (val.matches(passwordVal)) {
            InputPassword.setError("Password is too weak");
            return false;
        } else if(!val.equals(val2)){
            inputConfirmPassword.setError("Confirm password does not match with password");
            return false;
        }
        else {
            InputPassword.setError(null);
            return true;
        }

    }

    //After clicking the Signup button
    public void LoginScreen(View view){
        if ( !validateUsername() | !validateEmail() |  !validatePassword()) {
            return;
        }
        String patientusername = username.getText().toString();
        String patientemail = email.getText().toString();
        String patientpassword = InputPassword.getText().toString();
        String patientconfirmpassword = inputConfirmPassword.getText().toString();
        long started = System.currentTimeMillis();

        PatientModel patientModel=new PatientModel(patientusername,patientemail,patientpassword,patientconfirmpassword,started,0);
        dbHandler.addPatient(patientModel);

        Intent i=new Intent(MainActivity.this,LoginActivity.class);
        startActivity(i);

    }
}
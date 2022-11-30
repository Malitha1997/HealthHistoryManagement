package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import android.os.Bundle;

public class Home extends AppCompatActivity {
    TextView LifeCare;
    TextView text_add_prescription;
    TextView text_my_health_booklet;
    TextView text_side_effects;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        LifeCare=findViewById(R.id.LifeCare);
        text_add_prescription=findViewById(R.id.text_add_prescription);
        text_my_health_booklet=findViewById(R.id.text_my_health_booklet);
        text_side_effects=findViewById(R.id.text_side_effects);

        LifeCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Home.class);
                startActivity(i);
            }
        });

        text_add_prescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Add_prescription.class);
                startActivity(intent);
            }
        });

        text_my_health_booklet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),My_health_booklet.class);
                startActivity(i);
            }
        });

        text_side_effects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Side_effects.class);
                startActivity(i);
            }
        });
    }
}
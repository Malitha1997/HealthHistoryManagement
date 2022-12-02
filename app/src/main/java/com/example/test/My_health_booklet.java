package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class My_health_booklet extends AppCompatActivity {
    TextView lifeCareBooklet;
    TextView prescriptionView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_health_booklet);

        lifeCareBooklet=findViewById(R.id.lifeCareBooklet);
        prescriptionView=findViewById(R.id.prescriptionView);

        lifeCareBooklet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Home.class);
                startActivity(i);
            }
        });

        prescriptionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),View_search_prescription.class);
                startActivity(i);
            }
        });
    }

}
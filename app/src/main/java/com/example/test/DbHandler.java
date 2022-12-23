package com.example.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHandler extends SQLiteOpenHelper {
    private static final int VERSION=2;
    private static final String DB_NAME="lifecare";
    private static final String TABLE_NAME="patient";




    public DbHandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlPatient = "CREATE TABLE patients(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,blood_group TEXT,email TEXT,age TEXT,nic TEXT);";
        String sqlDisease= "CREATE TABLE diseases(id INTEGER PRIMARY KEY AUTOINCREMENT,disease_name TEXT);";
        String sqlPrescription= "CREATE TABLE precriptions(id INTEGER PRIMARY KEY AUTOINCREMENT,date TEXT,patient_id INTEGER, FOREIGN KEY(patient_id) REFERENCES patients(id),disease_id INTEGER,FOREIGN KEY(disease_id) REFERENCES diseases(id));";
        String sqlSideEffect= "CREATE TABLE sideeffects(id INTEGER PRIMARY KEY AUTOINCREMENT,side_effect TEXT,disease_id INTEGER, FOREIGN KEY(disease_id) REFERENCES diseases(id));";
        String sqlPatientDisease= "CREATE TABLE patient_diseases(patient_id INTEGER,FOREIGN KEY(patient_id) REFERENCES patients(id),disease_id INTEGER, FOREIGN KEY(disease_id) REFERENCES diseases(id));";

        sqLiteDatabase.execSQL(sqlPatient);
        sqLiteDatabase.execSQL(sqlDisease);
        sqLiteDatabase.execSQL(sqlPrescription);
        sqLiteDatabase.execSQL(sqlSideEffect);
        sqLiteDatabase.execSQL(sqlPatientDisease);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

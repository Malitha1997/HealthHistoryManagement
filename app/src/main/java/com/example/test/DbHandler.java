package com.example.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHandler extends SQLiteOpenHelper {
    private static final int VERSION=1;
    private static final String DB_NAME="lifecare";
    private static final String TABLE_NAME="patients";
    /*private static final String TABLE_NAME="diseases";
    private static final String TABLE_NAME="precriptions";
    private static final String TABLE_NAME="side_effects";
    private static final String TABLE_NAME="patient_diseases";
    private static final String TABLE_NAME="patient_side_effects";*/

    //patient table columns
    private static final String PATIENT_ID = "id";
    private static final String EMAIL = "email";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String CONFIRMPASSWORD = "confirmpassword";
    private static final String STARTED = "started";
    private static final String FINISHED = "finished";

    //disease table columns
    private static final String DISEASE_ID = "disease_id";
    private static final String DISEASE_NAME = "disease_name";

    //prescription columns
    private static final String PRESCRIPTION_ID = "prescription_id";
    private static final String DATE = "date";
    private static final String IMAGE = "image";

    //side_effects columns
    private static final String SIDE_EFFECT_ID = "side_effect_id";
    private static final String DESCRIPTION = "description";

    public DbHandler(Context context) {

        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //create patients table

        String TABLE_CREATE_PATIENT = "CREATE TABLE patients" +
                "("
                +PATIENT_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +EMAIL+ " TEXT,"
                +PASSWORD+ " TEXT,"
                +USERNAME+ " TEXT,"
                +STARTED+ " TEXT,"
                +FINISHED+ " TEXT" +
                ");";

        sqLiteDatabase.execSQL(TABLE_CREATE_PATIENT);


        //create diseases table
        String TABLE_CREATE_DISEASE = "CREATE TABLE diseases" +
                "("
                +DISEASE_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +DISEASE_NAME+ " TEXT" +
                ");";

        sqLiteDatabase.execSQL(TABLE_CREATE_DISEASE);

        //create prescriptions table
        String TABLE_CREATE_PRESCRIPTION = "CREATE TABLE prescriptions" +
                "("
                +PRESCRIPTION_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +DATE+ " TEXT,"
                +IMAGE+ " BLOB,"
                +PATIENT_ID+ " INTEGER REFERENCES patients, "
                +DISEASE_ID+ " INTEGER REFERENCES diseases" +
                ");";

        sqLiteDatabase.execSQL(TABLE_CREATE_PRESCRIPTION);


        //create side effects table
        String TABLE_CREATE_SIDE_EFFECT = "CREATE TABLE side_effects" +
                "("
                +SIDE_EFFECT_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +DESCRIPTION+ " TEXT,"
                +DISEASE_ID+ " INTEGER REFERENCES diseases" +
                ");";

        sqLiteDatabase.execSQL(TABLE_CREATE_SIDE_EFFECT);

        //create patient_diseases table
        String TABLE_CREATE_PATIENT_DISEASE = "CREATE TABLE patient_diseases" +
                "("
                +PATIENT_ID+ " INTEGER REFERENCES patients,"
                +DISEASE_ID+ " INTEGER REFERENCES diseases" +
                ");";

        sqLiteDatabase.execSQL(TABLE_CREATE_PATIENT_DISEASE);

        //create patient_side_effect table
        String TABLE_CREATE_PATIENT_SIDE_EFFECT = "CREATE TABLE patient_side_effects" +
                "("
                +PATIENT_ID+ " INTEGER REFERENCES patients,"
                +SIDE_EFFECT_ID+ " INTEGER REFERENCES side_effects" +
                ");";

        sqLiteDatabase.execSQL(TABLE_CREATE_PATIENT_SIDE_EFFECT);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DROP_TABLE_PATIENT= " DROP TABLE IF EXISTS patients";
        sqLiteDatabase.execSQL(DROP_TABLE_PATIENT);

        String DROP_TABLE_DISEASE= " DROP TABLE IF EXISTS diseases";
        sqLiteDatabase.execSQL(DROP_TABLE_DISEASE);

        String DROP_TABLE_PRESCRIPTION= " DROP TABLE IF EXISTS prescriptions";
        sqLiteDatabase.execSQL(DROP_TABLE_PRESCRIPTION);

        String DROP_TABLE_SIDE_EFFECT= " DROP TABLE IF EXISTS side_effects";
        sqLiteDatabase.execSQL(DROP_TABLE_SIDE_EFFECT);

        String DROP_TABLE_PATIENT_DISEASE= " DROP TABLE IF EXISTS patient_diseases";
        sqLiteDatabase.execSQL(DROP_TABLE_PATIENT_DISEASE);

        String DROP_TABLE_PATIENT_SIDE_EFFECT= " DROP TABLE IF EXISTS patient_side_effects";
        sqLiteDatabase.execSQL(DROP_TABLE_PATIENT_SIDE_EFFECT);

        onCreate(sqLiteDatabase);
    }

    //insert data into patient table
    public void addPatient(PatientModel patientModel){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(EMAIL,patientModel.getEmail());
        contentValues.put(USERNAME,patientModel.getUsername());
        contentValues.put(PASSWORD,patientModel.getPassword());
        contentValues.put(STARTED,patientModel.getStarted());
        contentValues.put(FINISHED,patientModel.getFinished());

        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        sqLiteDatabase.close();

    }

    public void addPrescription(PrescriptionModel prescriptionModel){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //contentValues.put(DATE,prescriptionModel getDate());

    }
}

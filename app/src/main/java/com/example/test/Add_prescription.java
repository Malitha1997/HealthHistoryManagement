package com.example.test;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.sql.Blob;
import java.util.Calendar;


@RequiresApi(api = Build.VERSION_CODES.M)
public class Add_prescription<storagePermission, cameraPermission, ActivityAdd_prescriptionBinding> extends AppCompatActivity {
    TextView lifeCareAddPrescription;
    ImageView pickImage;
    Button btn_upload;
    TextView inputDate;
    TextView inputDisease;
    EditText pres_date;
    EditText pres_disease;
    Bitmap bitmap;
    byte[] bitimg;
    private Bitmap ImageToStore;

    private DbHandler dbHandler;
    private Context context;
    public static final int CAMERA_REQUEST=100;
    public static final int STORAGE_REQUEST=101;
    String cameraPermission[];
    String storagePermission[];


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prescription);
        DatePickerDialog.OnDateSetListener setListener;

        cameraPermission = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        pres_date = findViewById(R.id.pres_date);
        pres_disease = findViewById(R.id.pres_disease);
        pickImage = findViewById(R.id.pickImage);
        btn_upload=findViewById(R.id.btn_upload);
        dbHandler = new DbHandler(context);

        /*Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);*/

        /*date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Add_prescription.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String d = day + "/" + month + "/" + year;
                        date.setText(d);
                    }
                },year,month,day);
                datePickerDialog.show();
                }
            });

        pres_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Add_prescription.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String d = day + "/" + month + "/" + year;
                        date.setText(d);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });*/


        /*setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                //date.setText(date);
            }
        });*/

        lifeCareAddPrescription=findViewById(R.id.lifeCareAddPrescription);
        lifeCareAddPrescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Home.class);
                startActivity(i);
            }
        });

        /*btn_upload=findViewById(R.id.btn_upload);
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String precriptionDate=date.getText().toString();
                String prescriptionDisease=disease.getText().toString();
                //ImageView prescriptionImage= (pickImage.getImageAlpha());
            }
        });*/

        pickImage=findViewById(R.id.pickImage);
        pickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int picd=0;
                if (picd==0){
                    if (!checkCameraPermission()){
                        requestCameraPermission();
                    }else{
                        pickFromGallery();
                    }
                }else if(picd==1){
                    if(checkStoragePermission()){
                        requestStoragePermission();
                    }else{
                        pickFromGallery();
                    }
                }
            }
        });
    }

    private boolean validateDate(){
        String val = pres_date.getText().toString();
        if(val.isEmpty()){
            pres_date.setError("Field can not be empty");
            return false;
        }
        else{
            pres_date.setError(null);
            return true;
        }
    }

    private boolean validateDisease(){
        String val = pres_disease.getText().toString();
        if(val.isEmpty()){
            pres_disease.setError("Field can not be empty");
            return false;
        }
        else{
            pres_disease.setError(null);
            return true;
        }
    }

    private boolean checkCameraPermission() {
        boolean result= ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)==(PackageManager.PERMISSION_GRANTED);
        boolean result1=ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED );
        return result && result1;

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestCameraPermission(){
        requestPermissions(cameraPermission,CAMERA_REQUEST);

    }

    private void pickFromGallery() {
        CropImage.activity().start(this);
    }

    private boolean checkStoragePermission() {
        boolean result= ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)==(PackageManager.PERMISSION_GRANTED);
        return result;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestStoragePermission(){
        requestPermissions(storagePermission,STORAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result=CropImage.getActivityResult(data);
            if (resultCode==RESULT_OK){
                Uri resultUri=result.getUri();
                Picasso.with(this).load(resultUri).into(pickImage);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case CAMERA_REQUEST:{
                if (grantResults.length>0) {
                    boolean camera_accepeted = grantResults[0] == (PackageManager.PERMISSION_GRANTED);
                    boolean storage_accepted = grantResults[1] == (PackageManager.PERMISSION_GRANTED);
                    if (storage_accepted) {
                        pickFromGallery();
                    } else {
                        Toast.makeText(this, "Please Enable the camera and storage permission! ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            break;
            case STORAGE_REQUEST:{
                if (grantResults.length>0){
                    boolean storage_accepted=grantResults[0]==(PackageManager.PERMISSION_GRANTED);
                    if (storage_accepted) {
                        pickFromGallery();
                    }else{
                        Toast.makeText(this, "Please enable storage permission!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            break;
        }
    }

    public void BookletScreen(View view){
        if ( !validateDate() | !validateDisease()) {
            return;
        }

        String presDate= pres_date.getText().toString();
        String presDisease= pres_disease.getText().toString();
        Bitmap pickImage = BitmapFactory.decodeResource(getResources(),R.drawable.add_prescription);

        PrescriptionModel prescriptionModel=new PrescriptionModel(presDate,presDisease,pickImage);
        dbHandler.addPrescription(prescriptionModel);

        Intent i=new Intent(Add_prescription.this,My_health_booklet.class);
        startActivity(i);

    }
}
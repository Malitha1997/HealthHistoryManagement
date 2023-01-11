package com.example.test;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
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
import android.widget.DatePicker;
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
    Button btnpresdate;
    TextView inputDate;
    TextView inputDisease;
    EditText pres_date;
    EditText pres_disease;
    Bitmap bitmap;
    byte[] bitimg;
    private Bitmap ImageToStore;
    private DatePickerDialog datePickerDialog;

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
        initDatePicker();

        cameraPermission = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        btnpresdate = findViewById(R.id.btnpresdate);
        pres_disease = findViewById(R.id.pres_disease);
        pickImage = findViewById(R.id.pickImage);
        btn_upload=findViewById(R.id.btn_upload);
        btnpresdate.setText(getTodaysDate());
        dbHandler = new DbHandler(context);

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

    private String getTodaysDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        month=month+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day,month,year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day,month,year);
                btnpresdate.setText(date);
            }
        };
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        int style= AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this,style,dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    //Date picker
    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " +day+ " "+year;
    }

    private String getMonthFormat(int month) {
        if(month==1)
            return "JAN";
        if(month==2)
            return "FEB";
        if(month==3)
            return "MAR";
        if(month==4)
            return "APR";
        if(month==5)
            return "MAY";
        if(month==6)
            return "JUN";
        if(month==7)
            return "JUL";
        if(month==8)
            return "AUG";
        if(month==9)
            return "SEP";
        if(month==10)
            return "OCT";
        if(month==11)
            return "NOV";
        if(month==12)
            return "DEC";

        return "JAN";
    }

    public void openDatePicker(View view){
        datePickerDialog.show();
    }

    //validation
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

    //Camera and storage permission
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

        /*String presDate= pres_date.getText().toString();
        String presDisease= pres_disease.getText().toString();
        Bitmap pickImage = BitmapFactory.decodeResource(getResources(),R.drawable.add_prescription);

        PrescriptionModel prescriptionModel=new PrescriptionModel(presDate,presDisease,pickImage);
        dbHandler.addPrescription(prescriptionModel);*/

        Intent i=new Intent(Add_prescription.this,My_health_booklet.class);
        startActivity(i);

    }
}
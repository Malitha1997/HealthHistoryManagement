package com.example.test;

import android.graphics.Bitmap;

import java.sql.Blob;

public class AddPrescriptionModel {
    private int id;
    private String date,disease;
    private Bitmap pickImage;

    public AddPrescriptionModel() {

    }

    public AddPrescriptionModel(int id, String date, String disease, Bitmap pickImage) {
        this.id = id;
        this.date = date;
        this.disease = disease;
        this.pickImage = pickImage;
    }

    public AddPrescriptionModel(String date, String disease, Bitmap pickImage) {
        this.date = date;
        this.disease = disease;
        this.pickImage = pickImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public Bitmap getPickImage() {
        return pickImage;
    }

    public void setPickImage(Bitmap pickImage) {
        this.pickImage = pickImage;
    }
}

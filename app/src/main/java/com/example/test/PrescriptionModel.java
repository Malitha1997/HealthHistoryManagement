package com.example.test;

import java.sql.Blob;

public class PrescriptionModel {
    private String id,date,disease;
    private Blob image;

    public PrescriptionModel(String id, String date, String disease, Blob image) {
        this.id = id;
        this.date = date;
        this.disease = disease;
        this.image = image;
    }

    public PrescriptionModel(String date, String disease, Blob image) {
        this.date = date;
        this.disease = disease;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }
}

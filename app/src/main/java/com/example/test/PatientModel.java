package com.example.test;

public class PatientModel {
    private String id,email,username,password;
    private long started,finished;

    public PatientModel(){

    }
    public PatientModel(String id, String email, String username, String password, long started, long finished) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.started = started;
        this.finished = finished;
    }

    public PatientModel(String email, String username, String password, long started, long finished) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.started = started;
        this.finished = finished;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getStarted() {
        return started;
    }

    public void setStarted(long started) {
        this.started = started;
    }

    public long getFinished() {
        return finished;
    }

    public void setFinished(long finished) {
        this.finished = finished;
    }
}

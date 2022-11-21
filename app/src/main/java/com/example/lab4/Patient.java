package com.example.lab4;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "patient")
public class Patient {
    @PrimaryKey
    private int patientId;
    private String firstName;
    private String lastName;
    private String department;
    private int nurseId;
    private int room;

    public Patient(int patientId, String firstName, String lastName, String department, int nurseId, int room) {
        setPatientId(patientId);
        setFirstName(firstName);
        setLastName(lastName);
        setDepartment(department);
        setNurseId(nurseId);
        setRoom(room);
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getNurseId() {
        return nurseId;
    }

    public void setNurseId(int nurseId) {
        this.nurseId = nurseId;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }
}

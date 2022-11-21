package com.example.lab4;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "test")
public class Test {
    @PrimaryKey
    private int testId;
    private int patientId;
    private int nurseId;
    private double bpl;
    private double bph;
    private double temperature;

    public Test(int testId, int patientId, int nurseId, double bpl, double bph, double temperature) {
        setTestId(testId);
        setPatientId(patientId);
        setNurseId(nurseId);
        setBpl(bpl);
        setBph(bph);
        setTemperature(temperature);
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getNurseId() {
        return nurseId;
    }

    public void setNurseId(int nurseId) {
        this.nurseId = nurseId;
    }

    public double getBpl() {
        return bpl;
    }

    public void setBpl(double bpl) {
        this.bpl = bpl;
    }

    public double getBph() {
        return bph;
    }

    public void setBph(double bph) {
        this.bph = bph;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}

package com.example.lab4;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class HospitalViewModel extends AndroidViewModel {
    private HospitalRepository repository;

    public HospitalViewModel(@NonNull Application application) {
        super(application);
        repository = new HospitalRepository(application);
    }

    /* Nurse CRUD */
    public void insertNurse(Nurse nurse) { repository.insertNurse(nurse);}

    public Nurse findNurseByIdAndPassword(int nurseId, String password) {
        return repository.findNurseByIdAndPassword(nurseId, password);
    }

    public void deleteAllNurses() {repository.deleteAllNurses();}

    /* Patient CRUD */
    public void insertPatient(Patient patient) { repository.insertPatient(patient);}

    public void updatePatient(Patient patient) { repository.updatePatient(patient);}

    public Patient findPatientByIdAndNurseId(int patientId, int nurseId) {
        return repository.findPatientByIdAndNurseId(patientId, nurseId);
    }

    public void deleteAllPatients() {repository.deleteAllPatients();}

    /* Test CRUD */
    public Test findTestByIdAndNurseId(int testId, int nurseId) {
        return repository.findTestByIdAndNurseId(testId, nurseId);
    }

    public int getCountTest() { return repository.getCountTest();}

    public void insertTest(Test test) { repository.insertTest(test);}

    public void deleteAllTests() {repository.deleteAllTests();}
}

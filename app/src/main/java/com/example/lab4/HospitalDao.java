package com.example.lab4;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface HospitalDao {
    /* Nurse CRUD */
    @Insert
    void insertNurse(Nurse nurse);

    @Query("SELECT * FROM nurse ORDER BY firstName")
    LiveData<List<Nurse>> getAllNurses();

    @Query("SELECT * FROM nurse WHERE nurseId = :nurseId AND password = :password")
    Nurse findNurseByIdAndPassword(int nurseId, String password);

    @Query("DELETE FROM nurse")
    void deleteAllNurses();

    /* Patient CRUD */
    @Query("SELECT * FROM patient WHERE patientId = :patientId AND nurseId = :nurseId")
    Patient findPatientByIdAndNurseId(int patientId, int nurseId);

    @Update
    void updatePatient(Patient patient);

    @Insert
    void insertPatient(Patient patient);

    @Query("DELETE FROM patient")
    void deleteAllPatients();

    /* Test CRUD */
    @Insert
    void insertTest(Test test);

    @Query("SELECT COUNT(*) FROM test")
    int getCountTest();

    @Query("DELETE FROM test")
    void deleteAllTests();

    @Query("SELECT * FROM test WHERE testId = :testId AND nurseId = :nurseId")
    Test findTestByIdAndNurseId(int testId, int nurseId);
}

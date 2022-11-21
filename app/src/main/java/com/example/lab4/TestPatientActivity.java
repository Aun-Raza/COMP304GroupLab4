package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TestPatientActivity extends AppCompatActivity {
    TextView editPatientIdTest;
    TextView editBPLTest;
    TextView editBPHTest;
    TextView editTemperatureTest;
    TextView tvInsertTestOutput;
    SharedPreferences sharedPreferences;
    int nurseId;
    HospitalViewModel hospitalViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_patient);

        editPatientIdTest = findViewById(R.id.editPatientIdTest);
        editBPLTest = findViewById(R.id.editBPLTest);
        editBPHTest = findViewById(R.id.editBPHTest);
        editTemperatureTest = findViewById(R.id.editTemperatureTest);

        tvInsertTestOutput = findViewById(R.id.tvInsertTestOutput);
        nurseId = getNurseId();
        hospitalViewModel = new ViewModelProvider(TestPatientActivity.this).get(HospitalViewModel.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        nurseId = getNurseId();
    }

    private int getNurseId() {
        sharedPreferences = getSharedPreferences("hospital_prefs", 0);
        int id = sharedPreferences.getInt("nurseId", -1);
        if (id < 0) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        return id;
    }

    public void testPatient(View view) {
        try {
            int patientId = Integer.parseInt(editPatientIdTest.getText().toString());
            Patient patient = hospitalViewModel.findPatientByIdAndNurseId(patientId, nurseId);
            if (patient == null) throw new Exception();
            double bpl = Double.parseDouble(editBPLTest.getText().toString());
            double bph = Double.parseDouble(editBPHTest.getText().toString());
            double temperature = Double.parseDouble(editTemperatureTest.getText().toString());
            int testId = hospitalViewModel.getCountTest() + 600;
            Test test = new Test(testId, patientId, nurseId, bpl, bph, temperature);
            hospitalViewModel.insertTest(test);
            tvInsertTestOutput.setText("Test is successfully created");
        } catch (Exception ex) {
            tvInsertTestOutput.setText("Either the patient does not exist or you are not authorized to test this patient");
        }
    }
}
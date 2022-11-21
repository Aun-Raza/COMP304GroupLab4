package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PatientActivity extends AppCompatActivity {
    TextView editPatientId;
    TextView tvPatientOutput;
    SharedPreferences sharedPreferences;
    int nurseId;
    HospitalViewModel hospitalViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        editPatientId = findViewById(R.id.editPatientId);
        tvPatientOutput = findViewById(R.id.tvPatientOutput);
        nurseId = getNurseId();
        hospitalViewModel = new ViewModelProvider(PatientActivity.this).get(HospitalViewModel.class);
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

    public void getPatient(View view) {
        try {
            int patientId = Integer.parseInt(editPatientId.getText().toString());
            Patient patient = hospitalViewModel.findPatientByIdAndNurseId(patientId, nurseId);
            tvPatientOutput.setText(String.format("ID: %d%nFull Name: %s %s%nRoom: %d", patient.getNurseId(),
                    patient.getFirstName(), patient.getLastName(), patient.getRoom()));
        } catch (Exception ex) {
            tvPatientOutput.setText("Either the patient does not exist or you are not authorized to view this patient");
        }
    }
}
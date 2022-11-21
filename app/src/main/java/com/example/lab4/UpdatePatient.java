package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UpdatePatient extends AppCompatActivity {
    TextView editPatientIdUpdate;
    TextView editPatientFirstName;
    TextView editPatientLastName;
    TextView editPatientRoom;
    LinearLayout updateFirstNameLL;
    LinearLayout updateLastNameLL;
    LinearLayout updateRoomLL;
    TextView tvUpdateOutput;
    Button btnUpdatePatientInfo;
    SharedPreferences sharedPreferences;
    Patient patient;
    int nurseId;
    HospitalViewModel hospitalViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_patient);

        editPatientIdUpdate = findViewById(R.id.editPatientIdUpdate);
        editPatientFirstName = findViewById(R.id.editPatientFirstName);
        editPatientLastName = findViewById(R.id.editPatientLastName);
        editPatientRoom = findViewById(R.id.editPatientRoom);

        updateFirstNameLL = findViewById(R.id.updateFirstNameLL);
        updateLastNameLL = findViewById(R.id.updateLastNameLL);
        updateRoomLL = findViewById(R.id.updateRoomLL);

        tvUpdateOutput = findViewById(R.id.tvUpdateOutput);
        btnUpdatePatientInfo = findViewById(R.id.btnUpdatePatientInfo);
        nurseId = getNurseId();
        hospitalViewModel = new ViewModelProvider(UpdatePatient.this).get(HospitalViewModel.class);
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

    public void updatePatient(View view) {
        if (patient == null) return;

        try {
            String firstName = editPatientFirstName.getText().toString();
            String lastName = editPatientLastName.getText().toString();
            int room = Integer.parseInt(editPatientRoom.getText().toString());
            patient.setFirstName(firstName);
            patient.setLastName(lastName);
            patient.setRoom(room);
            hospitalViewModel.updatePatient(patient);
            tvUpdateOutput.setText("Updated the patient with the new information");
        } catch (Exception ex) {
            tvUpdateOutput.setText("Something horribly went wrong");
        }
    }

    public void getPatientToUpdate(View view) {
        try {
            int patientId = Integer.parseInt(editPatientIdUpdate.getText().toString());
            patient = hospitalViewModel.findPatientByIdAndNurseId(patientId, nurseId);
            updateFirstNameLL.setVisibility(View.VISIBLE);
            updateLastNameLL.setVisibility(View.VISIBLE);
            updateRoomLL.setVisibility(View.VISIBLE);
            btnUpdatePatientInfo.setVisibility(View.VISIBLE);
            editPatientFirstName.setText(patient.getFirstName());
            editPatientLastName.setText(patient.getLastName());
            editPatientRoom.setText(patient.getRoom() + "");
        } catch (Exception ex) {
            tvUpdateOutput.setText("Either the patient does not exist or you are not authorized to view this patient");
            updateFirstNameLL.setVisibility(View.GONE);
            updateLastNameLL.setVisibility(View.GONE);
            updateRoomLL.setVisibility(View.GONE);
            btnUpdatePatientInfo.setVisibility(View.GONE);
        }
    }


}
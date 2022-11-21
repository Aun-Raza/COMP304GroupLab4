package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tvNurseId;
    Button btnLoginActivity;
    Button btnViewPatient;
    Button btnTestPatient;
    Button btnViewTestPatient;
    Button btnUpdatePatient;
    Button btnResetDB;
    HospitalViewModel hospitalViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLoginActivity = findViewById(R.id.btnLoginActivity);
        btnViewPatient = findViewById(R.id.btnViewPatient);
        btnTestPatient = findViewById(R.id.btnTestPatient);
        btnViewTestPatient = findViewById(R.id.btnViewTestPatient);
        btnUpdatePatient = findViewById(R.id.btnUpdatePatient);
        btnResetDB = findViewById(R.id.btnResetDB);
        tvNurseId = findViewById(R.id.tvNurseId);
        isLogin();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isLogin();
    }

    @SuppressLint("SetTextI18n")
    private void isLogin() {
        SharedPreferences sharedPreferences = getSharedPreferences("hospital_prefs", 0);
        int nurseId = sharedPreferences.getInt("nurseId", -1);

        if (nurseId >= 0) {
            tvNurseId.setText("Nurse " + nurseId + " Active");
            activateButtons(true);
            btnLoginActivity.setText("Logout");
            btnLoginActivity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("nurseId", -1);
                    editor.commit();
                    isLogin();
                }
            });
        }
        else {
            tvNurseId.setText("Please Login to access Patient Information");
            activateButtons(false);
            btnLoginActivity.setText("Login");
            btnLoginActivity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    public void onPatientActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), PatientActivity.class);
        startActivity(intent);
    }

    public void onTestActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), TestActivity.class);
        startActivity(intent);
    }

    public void onUpdatePatientActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), UpdatePatient.class);
        startActivity(intent);
    }

    public void onTestPatientActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), TestPatientActivity.class);
        startActivity(intent);
    }

    public void resetDatabase(View view) {
        hospitalViewModel = new ViewModelProvider(MainActivity.this).get(HospitalViewModel.class);

        hospitalViewModel.deleteAllNurses();
        hospitalViewModel.deleteAllPatients();
        hospitalViewModel.deleteAllTests();

        hospitalViewModel.insertNurse(new Nurse(400, "Abraham", "Lincoln", "Vaccinology", "12345"));
        hospitalViewModel.insertNurse(new Nurse(401,"Henry", "Ford", "Oncology", "12345"));
        hospitalViewModel.insertNurse(new Nurse(402,"Andrew", "Carnegie", "Observation", "12345"));

        hospitalViewModel.insertPatient(new Patient(100,"Theodore", "Roosevelt", "Fun room", 400, 1));
        hospitalViewModel.insertPatient(new Patient(101,"Benjamin", "Franklin", "ER", 401, 2));
        hospitalViewModel.insertPatient(new Patient(102,"Nikola", "Tesla", "Testing", 402, 3));

        hospitalViewModel.insertTest(new Test(600,100,400,80,120,35.5));
        hospitalViewModel.insertTest(new Test(601,101,401,87,136,36.1));
        hospitalViewModel.insertTest(new Test(602,102,402,133,199,34.8));

        Toast.makeText(getApplicationContext(), "Successfully restarted the database", Toast.LENGTH_SHORT).show();
    }

    private void activateButtons(boolean setter) {
        btnViewPatient.setEnabled(setter);
        btnTestPatient.setEnabled(setter);
        btnViewTestPatient.setEnabled(setter);
        btnUpdatePatient.setEnabled(setter);
        btnResetDB.setEnabled(!setter);
    }
}
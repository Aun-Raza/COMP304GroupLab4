package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    TextView editNurseId;
    TextView editNursePassword;
    TextView tvNurseOutput;
    Button btnLogin;
    HospitalViewModel hospitalViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editNurseId = findViewById(R.id.editNurseId);
        editNursePassword = findViewById(R.id.editNursePassword);
        tvNurseOutput = findViewById(R.id.tvNurseOutput);
        btnLogin = findViewById(R.id.btnLogin);
        hospitalViewModel = new ViewModelProvider(LoginActivity.this).get(HospitalViewModel.class);

    }

    public void login(View view) {
        try {
            int id = Integer.parseInt(editNurseId.getText().toString());
            String password = editNursePassword.getText().toString();
            Nurse nurse = hospitalViewModel.findNurseByIdAndPassword(id, password);
            tvNurseOutput.setText(String.format("ID: %d%nFull Name: %s %s%nDepartment: %s", nurse.getNurseId(),
                    nurse.getFirstName(), nurse.getLastName(), nurse.getDepartment()));

            SharedPreferences sharedPreferences = getSharedPreferences("hospital_prefs", 0);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("nurseId", nurse.getNurseId());
            editor.commit();
            Toast.makeText(getApplicationContext(), "You successfully login.", Toast.LENGTH_SHORT).show();

        } catch (Exception ex) {
            tvNurseOutput.setText("Please enter the correct email or password");
            Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
        }
    }

}
package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {
    TextView editTestId;
    TextView tvTestOutput;
    SharedPreferences sharedPreferences;
    int nurseId;
    HospitalViewModel hospitalViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        editTestId = findViewById(R.id.editTestId);
        tvTestOutput = findViewById(R.id.tvTestOutput);
        nurseId = getNurseId();
        hospitalViewModel = new ViewModelProvider(TestActivity.this).get(HospitalViewModel.class);
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

    public void onGetTest(View view) {
        try {
            int testId = Integer.parseInt(editTestId.getText().toString());
            Test test = hospitalViewModel.findTestByIdAndNurseId(testId, nurseId);
            tvTestOutput.setText(String.format("ID: %d%nBPH: %d%nBPL: %d%nTemperature %.2fF", test.getTestId(),
                    (int)test.getBph(), (int)test.getBpl(), test.getTemperature()));
        } catch (Exception ex) {
            tvTestOutput.setText("Either the test does not exist or you are not authorized to view this test");
        }
    }
}
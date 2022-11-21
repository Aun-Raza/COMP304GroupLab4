package com.example.lab4;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

public class HospitalRepository {
    private HospitalDao hospitalDao;

    public HospitalRepository(Application application) {
        HospitalDatabase database = HospitalDatabase.getInstance(application);
        hospitalDao = database.hospitalDao();
    }

    /* Nurse CRUD */
    public void insertNurse(Nurse nurse) {
        try {
            new insertNurseAsyncTask(hospitalDao).execute(nurse);
        } catch (Exception ex) {
            return;
        }
    }

    public Nurse findNurseByIdAndPassword(int nurseId, String password) {
        try {
            return new findNurseByIdAndPasswordAsyncTask(hospitalDao).execute(nurseId, password).get();
        } catch (Exception ex) {
            return null;
        }
    }

    public void deleteAllNurses() {
        try {
            new deleteAllNursesAsyncTask(hospitalDao).execute();
        } catch (Exception ex) {
            return;
        }
    }

    /* Nurse Async CRUD */
    private static class findNurseByIdAndPasswordAsyncTask extends AsyncTask<Object, Void, Nurse> {
        private HospitalDao hospitalDao;

        private findNurseByIdAndPasswordAsyncTask(HospitalDao hospitalDao) {
            this.hospitalDao = hospitalDao;
        }


        @Override
        protected Nurse doInBackground(Object... params) {
            int nurseId = Integer.parseInt(params[0].toString());
            String password = params[1].toString();
            Nurse nurse = hospitalDao.findNurseByIdAndPassword(nurseId, password);
            return nurse;
        }
    }

    private static class insertNurseAsyncTask extends AsyncTask<Nurse, Void, Void> {
        private HospitalDao hospitalDao;

        private insertNurseAsyncTask(HospitalDao hospitalDao) {
            this.hospitalDao = hospitalDao;
        }


        @Override
        protected Void doInBackground(Nurse... nurses) {
            hospitalDao.insertNurse(nurses[0]);
            return null;
        }
    }

    private static class deleteAllNursesAsyncTask extends AsyncTask<Void, Void, Void> {
        private HospitalDao hospitalDao;

        private deleteAllNursesAsyncTask(HospitalDao hospitalDao) {
            this.hospitalDao = hospitalDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            hospitalDao.deleteAllNurses();
            return null;
        }
    }

    /* Patient CRUD */
    public void insertPatient(Patient patient) {
        try {
            new insertPatientAsyncTask(hospitalDao).execute(patient);
        } catch (Exception ex) {
            return;
        }
    }

    public void deleteAllPatients() {
        try {
            new deleteAllPatientsAsyncTask(hospitalDao).execute();
        } catch (Exception ex) {
            return;
        }
    }

    public void updatePatient(Patient patient) {
        try {
            new updatePatientAsyncTask(hospitalDao).execute(patient);
        } catch (Exception ex) {
            return;
        }
    }

    public Patient findPatientByIdAndNurseId(int patientId, int nurseId) {
        try {
            return new getPatientByIdAndNurseId(hospitalDao).execute(patientId, nurseId).get();
        } catch (Exception ex) {
            return null;
        }
    }

    /* Patient Async CRUD */
    private static class getPatientByIdAndNurseId extends AsyncTask<Integer, Void, Patient> {
        private HospitalDao hospitalDao;

        private getPatientByIdAndNurseId(HospitalDao hospitalDao) {
            this.hospitalDao = hospitalDao;
        }

        @Override
        protected Patient doInBackground(Integer... integers) {
            int patientId = integers[0];
            int nurseId = integers[1];
            Patient patient = hospitalDao.findPatientByIdAndNurseId(patientId, nurseId);
            return patient;
        }
    }

    private static class updatePatientAsyncTask extends AsyncTask<Patient, Void, Void> {
        private HospitalDao hospitalDao;

        private updatePatientAsyncTask(HospitalDao hospitalDao) {
            this.hospitalDao = hospitalDao;
        }

        @Override
        protected Void doInBackground(Patient... patients) {
            hospitalDao.updatePatient(patients[0]);
            return null;
        }
    }

    private static class insertPatientAsyncTask extends AsyncTask<Patient, Void, Void> {
        private HospitalDao hospitalDao;

        private insertPatientAsyncTask(HospitalDao hospitalDao) {
            this.hospitalDao = hospitalDao;
        }

        @Override
        protected Void doInBackground(Patient... patients) {
            hospitalDao.insertPatient(patients[0]);
            return null;
        }
    }

    private static class deleteAllPatientsAsyncTask extends AsyncTask<Void, Void, Void> {
        private HospitalDao hospitalDao;

        private deleteAllPatientsAsyncTask(HospitalDao hospitalDao) {
            this.hospitalDao = hospitalDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            hospitalDao.deleteAllPatients();
            return null;
        }
    }

    /* Test CRUD */
    public Test findTestByIdAndNurseId(int testId, int nurseId) {
        try {
            return new findTestByIdAndNurseIdAsyncTask(hospitalDao).execute(testId, nurseId).get();
        } catch (Exception ex) {
            return null;
        }
    }

    public void insertTest(Test test) {
        try {
            new insertTestAsyncTask(hospitalDao).execute(test);
        } catch (Exception ex) {
            return;
        }
    }

    public int getCountTest() {
        try {
            return new getCountTestAsyncTask(hospitalDao).execute().get();
        } catch (Exception ex) {
            return -1;
        }
    }

    public void deleteAllTests() {
        try {
            new deleteAllTestsAsyncTask(hospitalDao).execute();
        } catch (Exception ex) {
            return;
        }
    }
    /* Test Async CRUD */
    private static class findTestByIdAndNurseIdAsyncTask extends AsyncTask<Integer, Void, Test> {
        private HospitalDao hospitalDao;

        private findTestByIdAndNurseIdAsyncTask(HospitalDao hospitalDao) {
            this.hospitalDao = hospitalDao;
        }

        @Override
        protected Test doInBackground(Integer... integers) {
            int testId = integers[0];
            int nurseId = integers[1];
            Test test = hospitalDao.findTestByIdAndNurseId(testId, nurseId);
            return test;
        }
    }

    private static class getCountTestAsyncTask extends AsyncTask<Void, Void, Integer> {
        private HospitalDao hospitalDao;

        private getCountTestAsyncTask(HospitalDao hospitalDao) {
            this.hospitalDao = hospitalDao;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            int count = hospitalDao.getCountTest();
            return count;
        }
    }

    private static class insertTestAsyncTask extends AsyncTask<Test, Void, Void> {
        private HospitalDao hospitalDao;

        private insertTestAsyncTask(HospitalDao hospitalDao) {
            this.hospitalDao = hospitalDao;
        }

        @Override
        protected Void doInBackground(Test... tests) {
            hospitalDao.insertTest(tests[0]);
            return null;
        }
    }

    private static class deleteAllTestsAsyncTask extends AsyncTask<Void, Void, Void> {
        private HospitalDao hospitalDao;

        private deleteAllTestsAsyncTask(HospitalDao hospitalDao) {
            this.hospitalDao = hospitalDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            hospitalDao.deleteAllTests();
            return null;
        }
    }
}

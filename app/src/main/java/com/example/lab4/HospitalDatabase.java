package com.example.lab4;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Patient.class, Test.class, Nurse.class}, version = 1)
public abstract class HospitalDatabase extends RoomDatabase {
    private static HospitalDatabase instance;

    public abstract HospitalDao hospitalDao();

    public static synchronized HospitalDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            HospitalDatabase.class, "WTF").build();
        }
        return instance;
    }
}

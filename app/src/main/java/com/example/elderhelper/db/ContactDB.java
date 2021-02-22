package com.example.elderhelper.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import com.example.elderhelper.model.Contact;
import com.example.elderhelper.model.Medication;

@Database(entities = {Contact.class, Medication.class}, version = 2)
public abstract class ContactDB extends RoomDatabase {

    private static ContactDB INSTANCE = null;

    public static ContactDB getInstance(Context context) {

        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, ContactDB.class, "Contact_db").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return INSTANCE;
    }
        public abstract ContactDao emergencyContactDao();

        public abstract SelectedMeds selectedMeds();
}



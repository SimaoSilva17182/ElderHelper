package com.example.elderhelper.db;

import android.content.Context;


import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import com.example.elderhelper.model.Contact;

@Database(entities = {Contact.class}, version = 1)
public abstract class ContactDB extends RoomDatabase {

    private static ContactDB INSTANCE = null;

    public static ContactDB getInstance(Context context) {

        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, ContactDB.class, "Contact_db").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return INSTANCE;
    }
        public abstract EmergencyContact emergencyContactDao();
        public abstract SelectedMeds selectedMedsDao();
}



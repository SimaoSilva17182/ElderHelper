package com.example.elderhelper.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.elderhelper.model.Contact;
import com.example.elderhelper.model.Medication;

import java.util.List;

@Dao
public interface EmergencyContact {

    @Insert
    long insert(Contact contact);

    @Delete
    int delete(Contact contact);

    @Update
    int update(Contact update);

    @Query("select * from Contact")
    List<Contact> getAll();

}

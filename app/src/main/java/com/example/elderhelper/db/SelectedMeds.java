package com.example.elderhelper.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.elderhelper.model.Medication;

import java.util.List;

@Dao
public interface SelectedMeds {

    @Insert
    long insert(Medication medication);

    @Delete
    int delete(Medication medication);

    @Update
    int update(Medication update);

    @Query("select * from Medication")
    List<Medication> getAll();
}

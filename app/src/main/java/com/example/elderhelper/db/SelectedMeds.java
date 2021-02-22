package com.example.elderhelper.db;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.elderhelper.model.Medication;

import java.util.List;

@Dao
public abstract class SelectedMeds implements EmergencyContact<Medication> {

    @Query("select * from Medication")
    public abstract List<Medication> getAll();
}

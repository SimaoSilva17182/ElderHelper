package com.example.elderhelper.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.elderhelper.model.Contact;

import java.util.List;

@Dao
public interface EmergencyContact <ENTITY>{

    @Insert
    long insert(ENTITY entity);

    @Delete
    int delete(ENTITY entity);

    @Update
    int update(ENTITY update);

}

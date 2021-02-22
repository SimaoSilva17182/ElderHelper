package com.example.elderhelper.db;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.elderhelper.model.Contact;

import java.util.List;

@Dao
public abstract class ContactDao implements EmergencyContact<Contact> {

    @Query("select * from Contact")
    public abstract List<Contact> getAll();
}

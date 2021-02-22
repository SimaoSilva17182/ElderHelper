package com.example.elderhelper.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.elderhelper.R;
import com.example.elderhelper.adapter.ContactAdapter;
import com.example.elderhelper.adapter.EmergencyCallAdapter;
import com.example.elderhelper.db.ContactDB;
import com.example.elderhelper.db.ContactDao;
import com.example.elderhelper.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class EmergencyCallActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EmergencyCallAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency__call);

        recyclerView = findViewById(R.id.EmergencyContact);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setAdapter();
    }

    private List<Contact> getLocalDB(){

        List<Contact> contact = ContactDB.getInstance(getApplicationContext()).emergencyContactDao().getAll();
        return contact;
    }

    private void setAdapter(){

        this.adapter = new EmergencyCallAdapter(getApplicationContext(),getLocalDB());

        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lm);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
package com.example.elderhelper.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elderhelper.R;
import com.example.elderhelper.adapter.MedicationAdapter;
import com.example.elderhelper.db.ContactDB;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class Medication extends AppCompatActivity {

    private FloatingActionButton addMedication;
    private Button makeOrder;

    RecyclerView recyclerView;
    MedicationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication);

        recyclerView = findViewById(R.id.medicationList);
        addMedication = findViewById(R.id.add_medication_btn);
        makeOrder = findViewById(R.id.order);

        addMedication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Medication.this, AddMeds.class);
                startActivity(intent);
            }
        });

        makeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(Medication.this, Order.class);
                //startActivity(intent);
            }
        });

    }
    private List<com.example.elderhelper.model.Medication> getLocalDB(){

        List<com.example.elderhelper.model.Medication> medication = ContactDB.getInstance(getApplicationContext()).selectedMedsDao().getAll();
        return medication;
    }

    private void setAdapter(){

        this.adapter = new MedicationAdapter(getApplicationContext(),getLocalDB());

        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lm);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


}
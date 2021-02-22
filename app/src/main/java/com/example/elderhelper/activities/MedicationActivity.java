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
import com.example.elderhelper.model.Medication;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MedicationActivity extends AppCompatActivity {

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
                Intent intent = new Intent(MedicationActivity.this, AddMedsActivity.class);
                startActivity(intent);
            }
        });

        /*makeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(MedicationActivity.this, OrderActivity.class);
               startActivity(intent);
            }
        });*/
    }

    @Override
    protected void onStart() {
        super.onStart();
        setAdapter();
    }

    private List<Medication> getLocalDB(){

        List<Medication> meds = new ArrayList<>();
        meds = ContactDB.getInstance(getApplicationContext()).selectedMeds().getAll();
        return meds;
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
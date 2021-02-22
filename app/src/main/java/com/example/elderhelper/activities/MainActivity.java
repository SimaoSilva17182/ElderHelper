package com.example.elderhelper.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.elderhelper.R;

public class MainActivity extends AppCompatActivity {
    private ImageButton contacts;
    private ImageButton medication;
    private ImageButton emergency_call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contacts = findViewById(R.id.Contacts);
        medication = findViewById(R.id.Medication);
        emergency_call = findViewById(R.id.Emergency_Call);

        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContactsActivity.class);
                startActivity(intent);
            }
        });

        medication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MedicationActivity.class);
                startActivity(intent);
            }
        });

        emergency_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EmergencyCallActivity.class);
                startActivity(intent);
            }
        });
    }
}

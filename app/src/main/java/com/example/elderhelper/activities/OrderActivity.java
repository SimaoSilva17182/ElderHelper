package com.example.elderhelper.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.elderhelper.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class OrderActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Button callMap;
    private Button order;
    private static final int REQUEST_LOCATION_CODE = 1003;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        callMap = findViewById(R.id.map_btn);
        order = findViewById(R.id.confirm_order);

        if (ContextCompat.checkSelfPermission(OrderActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(OrderActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_CODE);
        }

        Intent intent = getIntent();

        callMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  Intent intent1 = new Intent(OrderActivity.this, Maps.class);
                //startActivityForResult(intent1, REQUEST_LOCATION_CODE);
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(OrderActivity.this, MedicationActivity.class);
                startActivity(intent1);
                finish();
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
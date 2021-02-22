package com.example.elderhelper.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.elderhelper.R;
import com.example.elderhelper.db.ContactDB;
import com.example.elderhelper.model.Medication;

public class AddMedsActivity extends AppCompatActivity {

    private EditText addmedication;
    private Button confirm;
    private Button clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meds);

        addmedication = findViewById(R.id.addMedication);
        confirm = findViewById(R.id.confirm2);
        clear = findViewById(R.id.clear);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Medication medication = new Medication();
                medication.setMedication(addmedication.getText().toString());

               ContactDB.getInstance(getApplicationContext()).selectedMeds().insert(medication);

                final CharSequence[] options = {"Continue to add Meds", "Make order"};
                AlertDialog.Builder builder = new AlertDialog.Builder(AddMedsActivity.this); // this will create a pop-up dialog box that will ask the user to choose the options above
                builder.setTitle("Do you wish to continue to add medication?");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (options[item].equals("Continue to add Meds")) {
                            Intent intent = new Intent(AddMedsActivity.this, AddMedsActivity.class);
                            startActivity(intent);
                            finish();
                        } else if (options[item].equals("Make order")) {
                            {
                                Intent intent = new Intent(AddMedsActivity.this, Medication.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    }
                });
                builder.show();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddMedsActivity.this, AddMedsActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }
}

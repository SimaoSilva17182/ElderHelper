package com.example.elderhelper.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elderhelper.adapter.ContactAdapter;
import com.example.elderhelper.R;
import com.example.elderhelper.model.Contact;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class Contacts extends AppCompatActivity {

    private FloatingActionButton addContact;
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    RecyclerView recyclerView;
    ContactAdapter adapter;
    List <Contact> contactList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        recyclerView = findViewById(R.id.ContactList);
        addContact = findViewById(R.id.add_contact_btn);

        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Contacts.this, AddContact.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getInfo();
    }

    /**
     * Method to save
     */
    private void getInfo() {
        firebaseFirestore.collection("contact").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                contactList.clear();

                for(DocumentSnapshot documentSnapshot: task.getResult()) {

                    Contact contact = new Contact();
                    contact.setName(documentSnapshot.get("name").toString());
                    contact.setNumber(documentSnapshot.get("number").toString());
                    contact.setImageURL(documentSnapshot.get("imageURL").toString());
                    contactList.add(contact);
                }
                adapter = new ContactAdapter(getApplicationContext(),contactList);

                RecyclerView.LayoutManager lm = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(lm);
                recyclerView.setHasFixedSize(true);
                recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
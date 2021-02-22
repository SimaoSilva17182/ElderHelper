package com.example.elderhelper.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elderhelper.R;
import com.example.elderhelper.model.Contact;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    List<Contact> list;
    Context context;

    StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    StorageReference images = storageReference.child("images");

    public ContactAdapter(Context context, List<Contact> list1) {

        this.context = context;
        this.list = list1;

    }

    @NonNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View contactList = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_contact_item,parent,false); // this turns the activity_contact_item into a view
        return new ViewHolder(contactList); // this will return the view contactlist to the ViewHolder
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, int position) {

        Contact contact = list.get(position);

        StorageReference storage = images.child(contact.getName()+ ".jpg");

        holder.name.setText(contact.getName());
        holder.number.setText(contact.getNumber());
        storage.getDownloadUrl().addOnSuccessListener(uri -> Picasso.get().load(uri.toString()).into(holder.photo));
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView number;
        private TextView name;
        private ImageView photo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            number = itemView.findViewById(R.id.phone_number);
            name = itemView.findViewById(R.id.name);
            photo = itemView.findViewById(R.id.photo);
        }
    }
}

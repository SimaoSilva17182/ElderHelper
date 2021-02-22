package com.example.elderhelper.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elderhelper.R;
import com.example.elderhelper.model.Contact;

import java.util.List;

public class EmergencyCallAdapter extends RecyclerView.Adapter<EmergencyCallAdapter.ViewHolder> {

    List<Contact> list;
    Context context;

    public EmergencyCallAdapter(Context context, List<Contact> list1) {

        this.context = context;
        this.list = list1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View EmergencyContact = LayoutInflater.from(parent.getContext()).inflate(R.layout.emergency_call_item,parent,false); // this turns the activity_contact_item into a view
        return new ViewHolder(EmergencyContact);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Contact contact = list.get(position);

        holder.name.setText(contact.getName());
        holder.number.setText(contact.getNumber());
        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:+351"+ contact.getNumber()));
                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(callIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView number;
        private ImageButton call;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.medication_name);
            number = itemView.findViewById(R.id.PhoneNumber);
            call = itemView.findViewById(R.id.call);
        }
    }

}

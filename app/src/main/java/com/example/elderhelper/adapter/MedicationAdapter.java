package com.example.elderhelper.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elderhelper.R;
import com.example.elderhelper.activities.OrderActivity;
import com.example.elderhelper.db.ContactDB;
import com.example.elderhelper.model.Contact;
import com.example.elderhelper.model.Medication;

import java.util.ArrayList;
import java.util.List;

public class MedicationAdapter extends RecyclerView.Adapter<MedicationAdapter.ViewHolder> {

    List<Medication> list;
    Context context;

    public MedicationAdapter(Context context, List<Medication> list1) {

        this.context = context;
        this.list = list1;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View medicationList = LayoutInflater.from(parent.getContext()).inflate(R.layout.medication_list,parent,false); // this turns the activity_contact_item into a view
        return new ViewHolder(medicationList); // this will return the view contactlist to the ViewHolder
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Medication medication = list.get(position);

        holder.name.setText(medication.getMedication());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderActivity.class);
                intent.putExtra("medication",medication.getMedication());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.medication_name);
        }
    }
}

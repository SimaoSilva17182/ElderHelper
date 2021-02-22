package com.example.elderhelper.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

    @Entity
    public class Medication {

        @PrimaryKey(autoGenerate = true)
        private long id;

        private String medication;
        private Boolean selected;

        public Medication(){

        }

        @Ignore
        public Medication(String medication) {

            this.medication = medication;
        }

        /** Getters ans Setters
         */

        public long getId() {return id; }

        public void setId(long id) {this.id = id; }

        public String getMedication() { return medication; }

        public void setMedication(String medication) { this.medication = medication; }

        public Boolean getSelected() {return selected; }

        public void setSelected(Boolean selected) {this.selected = selected; }

    }


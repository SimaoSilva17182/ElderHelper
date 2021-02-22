package com.example.elderhelper.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Contact {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String name;
    private String number;
    private String ImageURL;

    public Contact(){

    }

    @Ignore
    public Contact(String name, String number, String imageURL) {

        this.name = name;
        this.number = number;
        ImageURL = imageURL;
    }

    /** Getters ans Setters
     */

    public long getId() {return id; }

    public void setId(long id) {this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getNumber() { return number; }

    public void setNumber(String number) { this.number = number; }

    public String getImageURL() { return ImageURL; }

    public void setImageURL(String imageURL) { ImageURL = imageURL; }
}

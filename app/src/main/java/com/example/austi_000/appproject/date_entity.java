package com.example.austi_000.appproject;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "dates")
public class date_entity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String date;
    public String title;
    public String des;
    public String startTime;
    public String endTime;
    public String allDay;
}
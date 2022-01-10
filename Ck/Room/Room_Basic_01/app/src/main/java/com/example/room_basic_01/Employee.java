package com.example.room_basic_01;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Employee
        implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public long employId;

    @ColumnInfo(name = "employ_name")
    public String name;
    public String designation;
}

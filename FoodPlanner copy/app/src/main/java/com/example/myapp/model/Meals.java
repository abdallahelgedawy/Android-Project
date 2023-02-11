package com.example.myapp.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "meals")
public class Meals {
    @PrimaryKey
    @NonNull
    String mealName;
}

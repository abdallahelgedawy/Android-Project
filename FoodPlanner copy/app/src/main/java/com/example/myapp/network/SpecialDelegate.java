package com.example.myapp.network;

import com.example.myapp.model.Meals;

import java.util.ArrayList;

public interface SpecialDelegate {
    public ArrayList<Meals> onpecialResult(ArrayList<Meals> meals);
}

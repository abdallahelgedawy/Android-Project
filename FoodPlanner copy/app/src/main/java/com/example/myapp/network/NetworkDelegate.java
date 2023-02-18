package com.example.myapp.network;

import com.example.myapp.model.Meals;

import java.util.ArrayList;

public interface NetworkDelegate {
    public void onSuccessResult(ArrayList<Meals> meals);
    public void onFailureResult(String errormMsg);


}


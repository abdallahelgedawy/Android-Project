package com.example.myapp.search.byIngredients.view;

import com.example.myapp.model.Meals;

import java.util.ArrayList;

public interface Search_by_ingredientsInterface {
    public void showData(ArrayList<Meals> meals);
    public void addToFAv(Meals meals);
}

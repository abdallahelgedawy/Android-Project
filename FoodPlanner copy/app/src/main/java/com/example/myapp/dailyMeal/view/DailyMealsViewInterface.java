package com.example.myapp.dailyMeal.view;

import com.example.myapp.model.Meals;

import java.util.ArrayList;

public interface DailyMealsViewInterface {
    public void showData(ArrayList<Meals> products);
    public void showallData(ArrayList<Meals> products);
    public void addToFav(Meals product);
    public void deleteFav(Meals meals);
}

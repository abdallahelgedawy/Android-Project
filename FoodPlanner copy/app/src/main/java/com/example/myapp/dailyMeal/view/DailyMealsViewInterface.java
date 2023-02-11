package com.example.myapp.dailyMeal.view;

import com.example.myapp.model.DailyMeals;
import com.example.myapp.model.Meals;

import java.util.ArrayList;
import java.util.List;

public interface DailyMealsViewInterface {
    public void showData(ArrayList<Meals> products);
    public void addToFav(Meals product);
}

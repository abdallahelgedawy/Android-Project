package com.example.myapp.search.category.view;

import com.example.myapp.model.Category;
import com.example.myapp.model.Meals;

import java.util.ArrayList;

public interface CategoryMealsViewInterface {
    public void showData(ArrayList<Meals> meals);
    public void addToFAv(Meals meals);
}

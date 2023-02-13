package com.example.myapp.db;


import com.example.myapp.model.Meals;

import java.util.List;

public interface LocalSource {


    public void delete(Meals meals);
    public void insert(Meals meals);
    public List<Meals> getFavoriteMeals();
}

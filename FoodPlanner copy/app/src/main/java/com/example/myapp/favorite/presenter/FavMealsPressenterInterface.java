package com.example.myapp.favorite.presenter;


import com.example.myapp.model.Meals;

public interface FavMealsPressenterInterface {
    public void getFavMeals();
    public void delete(Meals meals);
}

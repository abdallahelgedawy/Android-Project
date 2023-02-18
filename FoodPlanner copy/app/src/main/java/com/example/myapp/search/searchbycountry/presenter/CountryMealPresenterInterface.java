package com.example.myapp.search.searchbycountry.presenter;

import com.example.myapp.model.Meals;

public interface CountryMealPresenterInterface {
    public void getCountryMeal(String name);
    public void addToFav(Meals meals);
}

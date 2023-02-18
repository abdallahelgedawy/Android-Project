package com.example.myapp.search.category.presenter;

import com.example.myapp.model.Meals;

public interface CategoryMealsPresenterInterface {
    public void getCategoryName(String name);
    public void addToFav(Meals meals);
}

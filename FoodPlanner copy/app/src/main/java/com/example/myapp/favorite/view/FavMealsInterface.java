package com.example.myapp.favorite.view;


import com.example.myapp.model.Meals;

import java.util.List;

public interface FavMealsInterface {
    public void showFavData (List<Meals> meals);
    public void deleteProduct(Meals meals);
}

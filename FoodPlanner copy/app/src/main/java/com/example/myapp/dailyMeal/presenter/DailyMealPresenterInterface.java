package com.example.myapp.dailyMeal.presenter;

import com.example.myapp.model.Meals;

import java.util.List;

public interface DailyMealPresenterInterface {

   public void addToFav(Meals meals);
    public void getMeal();
  public void getAllMeal(String s);
  public void showFavData (List<Meals> meals);
 // public void deleteProduct(Meals meals);
}

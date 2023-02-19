package com.example.myapp.planMeals.view;

import com.example.myapp.model.Meals;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public interface PlanViewInterface {
     public void showDataSaturday(Observable<List<Meals>> meals);
     public void showDataSunday(Observable<List<Meals>> meals);

     public void showDataMonday(Observable<List<Meals>> meals);

     public void showDataTuesday(Observable<List<Meals>> meals);
     public void showDataWednesday(Observable<List<Meals>> meals);
     public void showDataThursday(Observable<List<Meals>> meals);
     public void showDataFriday(Observable<List<Meals>> meals);
     public void deleteMeal(Meals meals);
}

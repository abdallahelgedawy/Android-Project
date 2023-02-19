package com.example.myapp.db;


import androidx.room.Query;

import com.example.myapp.model.Meals;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public interface LocalSource {


    public void delete(Meals meals);
    public void insert(Meals meals);
    public List<Meals> getFavoriteMeals();
    public Observable<List<Meals>> getMealsSaturday();
    public Observable<List<Meals>> getMealsSunday();
    public Observable<List<Meals>> getMealsMonday();
    public Observable<List<Meals>> getMealsTuesday();
    public Observable<List<Meals>> getMealsWednesday();
    public Observable<List<Meals>> getMealsThursday();
    public Observable<List<Meals>> getMealsFriday();
    public void deleteAllmeals();
}

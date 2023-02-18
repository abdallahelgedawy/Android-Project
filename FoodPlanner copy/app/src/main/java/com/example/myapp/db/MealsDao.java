package com.example.myapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myapp.model.Meals;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;


@Dao
public interface MealsDao {
        @Query("SELECT * From meals WHERE day LIKE '0'")
        Observable<List<Meals>> getMeals();
        @Query("SELECT * From meals WHERE day LIKE '1'")
        Observable<List<Meals>>getMealsSaturday();
        @Query("SELECT * From meals WHERE day LIKE '2'")
        Observable<List<Meals>> getMealsSunday();
        @Query("SELECT * From meals WHERE day LIKE '3'")
        Observable<List<Meals>> getMealsMonday();
        @Query("SELECT * From meals WHERE day LIKE '4'")
        Observable<List<Meals>> getMealsTuesday();
        @Query("SELECT * From meals WHERE day LIKE '5'")
        Observable<List<Meals>> getMealsWednesday();
        @Query("SELECT * From meals WHERE day LIKE '6'")
        Observable<List<Meals>> getMealsThursday();
        @Query("SELECT * From meals WHERE day LIKE '7'")
        Observable<List<Meals>> getMealsFriday();
        @Insert(onConflict = OnConflictStrategy.IGNORE)
        Completable insertProduct (Meals meals);
        @Delete
        Completable deleteProduct (Meals meals);

        
    }


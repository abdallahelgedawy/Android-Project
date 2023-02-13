package com.example.myapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myapp.model.Meals;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;


@Dao
public interface MealsDao {
        @Query("SELECT * From meals")
        Observable<List<Meals>> getMeals();
        @Insert(onConflict = OnConflictStrategy.IGNORE)
        Completable insertProduct (Meals meals);
        @Delete
        Completable deleteProduct (Meals meals);

        
    }


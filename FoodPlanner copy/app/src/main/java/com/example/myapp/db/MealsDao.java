package com.example.myapp.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myapp.model.Meals;

import java.util.List;
@Dao
public interface MealsDao {

    public interface ProductDao {
        @Query("SELECT * From meals")
        LiveData<List<Meals>> getMeals();
        @Insert(onConflict = OnConflictStrategy.IGNORE)
        void insertProduct (Meals meals);
        @Delete
        void deleteProduct (Meals meals);
    }
}

package com.example.myapp.model;

import android.content.Context;

import com.example.myapp.dailyMeal.view.DailyMealActivity;
import com.example.myapp.db.LocalSource;
import com.example.myapp.network.CategoryDelegate;
import com.example.myapp.network.MealsClient;
import com.example.myapp.network.NetworkDelegate;
import com.example.myapp.network.RemoteSource;
import com.example.myapp.network.SpecialDelegate;

import java.util.List;

public class Repository implements RepositoryInterface  {

        RemoteSource remoteSource;
        private Context context;
        LocalSource localSource;
        private static Repository repository = null;

    public Repository(RemoteSource remoteSource, Context context, LocalSource localSource) {
        this.remoteSource = remoteSource;
        this.context = context;
        this.localSource = localSource;
    }

    public static Repository getInstance(RemoteSource remoteSource, LocalSource localSource,Context context){
        if(repository ==null){
            repository = new Repository(remoteSource, context,localSource);
        }
        return repository;
    }

    @Override
    public void getDailyMeal(NetworkDelegate networkDelegate) {
        remoteSource.getDailyMeal(networkDelegate);
    }

    @Override
    public void getAllMeal(SpecialDelegate networkDelegate) {
        remoteSource.getAllMeal(networkDelegate);

    }

    @Override
    public void getCountryMeal(NetworkDelegate networkDelegate, String name) {
        remoteSource.getCountryMeal(networkDelegate,name);

    }

    @Override
    public void getCategory(CategoryDelegate networkDelegate) {
        remoteSource.getCategory(networkDelegate);
    }

    @Override
    public void getCategorybyname(NetworkDelegate networkDelegate, String name) {
        remoteSource.getCategorybyname(networkDelegate , name);
    }

    @Override
    public void getIngredients(NetworkDelegate networkDelegate) {
        remoteSource.getIngredients(networkDelegate);
    }

    @Override
    public void getIngredientsname(NetworkDelegate networkDelegate, String name) {
        remoteSource.getIngredientsname(networkDelegate , name);
    }


    @Override
    public void delete(Meals meals) {
        localSource.delete(meals);
    }

    @Override
    public void insert(Meals meals) {
        localSource.insert(meals);

    }

    @Override
    public List<Meals> getFavoriteMeals() {
        return localSource.getFavoriteMeals();
    }
}


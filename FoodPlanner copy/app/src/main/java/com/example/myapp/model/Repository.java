package com.example.myapp.model;

import android.content.Context;

import com.example.myapp.db.LocalSource;
import com.example.myapp.network.CategoryDelegate;
import com.example.myapp.network.NetworkDelegate;
import com.example.myapp.network.RemoteSource;
import com.example.myapp.network.SpecialDelegate;

import java.util.List;

import io.reactivex.Observable;

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
    public void getAllMeal(SpecialDelegate networkDelegate , String s) {
        remoteSource.getAllMeal(networkDelegate , s);

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
    public void getMeal(NetworkDelegate networkDelegate, String name) {
        remoteSource.getMeal(networkDelegate , name);
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

    @Override
    public Observable<List<Meals>> getMealsSaturday() {
        return localSource.getMealsSaturday();
    }

    @Override
    public Observable<List<Meals>> getMealsSunday() {
        return localSource.getMealsSunday();
    }

    @Override
    public Observable<List<Meals>> getMealsMonday() {
        return localSource.getMealsMonday();
    }

    @Override
    public Observable<List<Meals>> getMealsTuesday() {
        return localSource.getMealsTuesday();
    }

    @Override
    public Observable<List<Meals>> getMealsWednesday() {
        return localSource.getMealsWednesday();
    }

    @Override
    public Observable<List<Meals>> getMealsThursday() {
        return localSource.getMealsThursday();
    }

    @Override
    public Observable<List<Meals>> getMealsFriday() {
        return localSource.getMealsFriday();
    }

}


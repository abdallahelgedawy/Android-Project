package com.example.myapp.search.category.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.myapp.model.Category;
import com.example.myapp.model.Meals;
import com.example.myapp.model.Repository;
import com.example.myapp.network.CategoryDelegate;
import com.example.myapp.network.NetworkDelegate;
import com.example.myapp.search.category.view.CategoryMealsViewInterface;
import com.example.myapp.search.category.view.CategoryViewInterface;

import java.util.ArrayList;


public class CategoryMealsPresenter implements CategoryMealsPresenterInterface , NetworkDelegate {
    Context context;
    private CategoryMealsViewInterface categoryMealsViewInterface;
    Repository repo;


    public CategoryMealsPresenter(Repository repo, CategoryMealsViewInterface categoryMealsViewInterface , Context context){
        this.repo = repo;
        this.categoryMealsViewInterface = categoryMealsViewInterface;
        this.context = context;
    }


    @Override
    public void getCategoryName(String name) {
repo.getCategorybyname( this,name);
    }

    @Override
    public void addToFav(Meals meals) {
        repo.insert(meals);
    }


    @Override
    public void onSuccessResult(ArrayList<Meals> meals) {
        categoryMealsViewInterface.showData(meals);

    }

    @Override
    public void onFailureResult(String errormMsg) {

    }
}

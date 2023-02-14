package com.example.myapp.search.byIngredients.presenter;

import android.content.Context;

import com.example.myapp.model.Meals;
import com.example.myapp.model.Repository;
import com.example.myapp.network.NetworkDelegate;
import com.example.myapp.search.byIngredients.view.IngredientsViewInterface;
import com.example.myapp.search.category.view.CategoryMealsViewInterface;

import java.util.ArrayList;

public class IngredientsPresenter implements IngredientsPresenterInterface , NetworkDelegate {
    Context context;
    IngredientsViewInterface ingredientsViewInterface;
    Repository repo;
    public IngredientsPresenter(Repository repo, IngredientsViewInterface ingredientsViewInterface , Context context){
        this.repo = repo;
        this.ingredientsViewInterface = ingredientsViewInterface;
        this.context = context;
    }

    @Override
    public void onSuccessResult(ArrayList<Meals> meals) {
        ingredientsViewInterface.showData(meals);
    }

    @Override
    public void onFailureResult(String errormMsg) {

    }

    @Override
    public void getIngredients() {
        repo.getIngredients(this);
    }
}

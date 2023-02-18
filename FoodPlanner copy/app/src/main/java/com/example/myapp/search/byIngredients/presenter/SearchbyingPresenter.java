package com.example.myapp.search.byIngredients.presenter;

import android.content.Context;

import com.example.myapp.model.Meals;
import com.example.myapp.model.Repository;
import com.example.myapp.network.NetworkDelegate;
import com.example.myapp.search.byIngredients.view.Search_by_ingredientsInterface;
import com.example.myapp.search.category.view.CategoryMealsViewInterface;

import java.util.ArrayList;

public class SearchbyingPresenter implements SearchbyingPresenterInterface , NetworkDelegate {
    Context context;
    Search_by_ingredientsInterface search;
    Repository repo;
       public SearchbyingPresenter(Repository repo, Search_by_ingredientsInterface search , Context context){
        this.repo = repo;
        this.search = search;
        this.context = context;
    }

    @Override
    public void onSuccessResult(ArrayList<Meals> meals) {
           search.showData(meals);

    }

    @Override
    public void onFailureResult(String errormMsg) {

    }

    @Override
    public void getIngName(String name) {
repo.getIngredientsname(this , name);
    }

    @Override
    public void addToFav(Meals meals) {
        repo.insert(meals);
    }
}

package com.example.myapp.search.searchbycountry.presenter;

import android.content.Context;
import android.widget.Toast;
import com.example.myapp.model.Meals;
import com.example.myapp.model.Repository;
import com.example.myapp.network.NetworkDelegate;
import com.example.myapp.search.searchbycountry.view.CountryMealsActivity;
import com.example.myapp.search.searchbycountry.view.CountryMealsViewInterface;

import java.util.ArrayList;

public class CountryMealPresenter implements CountryMealPresenterInterface, NetworkDelegate{
    Context context;
    private CountryMealsViewInterface countryMealsViewInterface;
    Repository repo;

    public CountryMealPresenter(Repository repo, CountryMealsViewInterface countryMealsViewInterface,Context context ) {
        this.repo = repo;
        this.countryMealsViewInterface = countryMealsViewInterface;
        this.context = context;

    }

    @Override
    public void onSuccessResult(ArrayList<Meals> meals) {
        countryMealsViewInterface.showCountryData(meals);
    }

    @Override
    public void onFailureResult(String errormMsg) {
        Toast.makeText(context, "no Data to show", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void getCountryMeal(String name) {
        repo.getCountryMeal(this,name);
    }

    @Override
    public void addToFav(Meals meals) {
        repo.insert(meals);
        
    }
}

package com.example.myapp.dailyMeal.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.myapp.dailyMeal.view.DailyMealsViewInterface;
import com.example.myapp.dailyMeal.view.OnClickFavorite;
import com.example.myapp.favorite.view.FavMealsInterface;
import com.example.myapp.model.Meals;
import com.example.myapp.model.Repository;
import com.example.myapp.network.NetworkDelegate;
import com.example.myapp.network.SpecialDelegate;

import java.util.ArrayList;
import java.util.List;

public class DailyMealPresenter implements DailyMealPresenterInterface , NetworkDelegate , SpecialDelegate  {

    Context context;
    private DailyMealsViewInterface dailyMealsViewInterface;
    Repository repo;
    private FavMealsInterface view;

    public DailyMealPresenter(Repository repo, DailyMealsViewInterface dailyMealsViewInterface , Context context){
        this.repo = repo;
        this.dailyMealsViewInterface = dailyMealsViewInterface;
        this.context = context;
    }



    @Override
    public void addToFav(Meals meals) {
        repo.insert(meals);

    }


    @Override
    public void getMeal() {
         repo.getDailyMeal(this);
    }

    @Override
    public void getAllMeal(String s) {
        repo.getAllMeal(this ,s );
    }

    @Override
    public void showFavData(List<Meals> meals) {
        view.showFavData(repo.getFavoriteMeals());

    }



    @Override
    public void onSuccessResult(ArrayList<Meals> meals) {
        Log.i("TAG", "onSuccessResult: " + "succes");
        dailyMealsViewInterface.showData(meals);
    }

    @Override
    public void onFailureResult(String errormMsg) {
        Log.i("TAG", "onFailureResult: " + errormMsg);
        Toast.makeText(context, "no Data to show", Toast.LENGTH_SHORT).show();

    }

    @Override
    public ArrayList<Meals> onpecialResult(ArrayList<Meals> meals) {
        dailyMealsViewInterface.showallData(meals);
        return meals;
    }


}

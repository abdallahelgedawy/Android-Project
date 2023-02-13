package com.example.myapp.favorite.presenter;

import android.content.Context;
import com.example.myapp.favorite.view.FavMealsInterface;
import com.example.myapp.model.Meals;
import com.example.myapp.model.Repository;

public class FavMealPressenter implements FavMealsPressenterInterface {
    private FavMealsInterface view;
    private Repository repo;
    Context context;
    public FavMealPressenter(Repository repo, Context context, FavMealsInterface view)
    {
        this.repo=repo;
        this.context=context;
        this.view=view;

    }

    @Override
    public void getFavMeals() {
        view.showFavData(repo.getFavoriteMeals());
    }

    @Override
    public void delete(Meals meals) {
        repo.delete(meals);
        getFavMeals();
    }
}

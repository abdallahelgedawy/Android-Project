package com.example.myapp.planMeals.presenter;

import android.content.Context;

import com.example.myapp.favorite.view.FavMealsInterface;
import com.example.myapp.model.Meals;
import com.example.myapp.model.Repository;
import com.example.myapp.planMeals.view.PlanViewInterface;

public class PlanPresenter implements PlanPresenterInterface {
    private PlanViewInterface view;
    private Repository repo;
    Context context;
    public PlanPresenter(Repository repo, Context context, PlanViewInterface view)
    {
        this.repo=repo;
        this.context=context;
        this.view=view;

    }
    @Override
    public void getFavMeals() {

    }

    @Override
    public void delete(Meals meals) {
     repo.delete(meals);
    }

    @Override
    public void getSaturday() {
        view.showDataSaturday(repo.getMealsSaturday());
    }

    @Override
    public void getSunday() {
        view.showDataSunday(repo.getMealsSunday());
    }

    @Override
    public void getMonday() {
        view.showDataMonday(repo.getMealsMonday());


    }

    @Override
    public void getTuesday() {
        view.showDataTuesday(repo.getMealsTuesday());

    }

    @Override
    public void getWednesday() {
        view.showDataWednesday(repo.getMealsWednesday());
    }

    @Override
    public void getThursday() {
    view.showDataThursday(repo.getMealsThursday());
    }

    @Override
    public void getFriday() {
        view.showDataFriday(repo.getMealsFriday());

    }
}

package com.example.myapp.detailedmeal.Presenter;

import android.content.Context;

import com.example.myapp.detailedmeal.view.DetailedviewInteface;
import com.example.myapp.model.Meals;
import com.example.myapp.model.Repository;
import com.example.myapp.network.NetworkDelegate;

import java.util.ArrayList;

public class DetailsPresenter implements DetailsPresenterInterface ,NetworkDelegate{
    Context context;
   DetailedviewInteface detailedviewInteface;
    ArrayList<Meals> meals;
    public DetailsPresenter(Context context, DetailedviewInteface detailedviewInteface, Repository repo) {
        this.context = context;
        this.detailedviewInteface = detailedviewInteface;
        this.repo = repo;
        this.meals = new ArrayList<>();
    }

    Repository repo;

    @Override
    public void getName(String name) {
        repo.getMeal(this , name);
    }

    @Override
    public void onSuccessResult(ArrayList<Meals> meals) {
        detailedviewInteface.showData(meals);
    }

    @Override
    public void onFailureResult(String errormMsg) {

    }
}

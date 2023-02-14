package com.example.myapp.search.category.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.myapp.dailyMeal.presenter.DailyMealPresenterInterface;
import com.example.myapp.dailyMeal.view.DailyMealsViewInterface;
import com.example.myapp.model.Category;
import com.example.myapp.model.Meals;
import com.example.myapp.model.Repository;
import com.example.myapp.network.CategoryDelegate;
import com.example.myapp.network.NetworkDelegate;
import com.example.myapp.search.category.view.CategoryViewInterface;

import java.util.ArrayList;

public class CategoryPresenter implements CategoryPresenterInterface, CategoryDelegate {

        Context context;
        private CategoryViewInterface categoryViewInterface;
        Repository repo;


        public CategoryPresenter(Repository repo, CategoryViewInterface categoryViewInterface , Context context){
            this.repo = repo;
            this.categoryViewInterface = categoryViewInterface;
            this.context = context;
        }

        @Override
        public void successCategory(ArrayList<Category> meals) {
            categoryViewInterface.showData(meals);
        }

    @Override
    public void getCategory() {
            repo.getCategory(this);

    }
}



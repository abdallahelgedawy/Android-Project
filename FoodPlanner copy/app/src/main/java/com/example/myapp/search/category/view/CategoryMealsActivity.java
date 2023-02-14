package com.example.myapp.search.category.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import com.example.myapp.R;
import com.example.myapp.db.ConcreteLocalSource;
import com.example.myapp.model.Category;
import com.example.myapp.model.Meals;
import com.example.myapp.model.Repository;
import com.example.myapp.network.MealsClient;
import com.example.myapp.search.category.presenter.CategoryMealsPresenter;
import com.example.myapp.search.category.presenter.CategoryMealsPresenterInterface;
import com.example.myapp.search.category.presenter.CategoryPresenterInterface;

import java.util.ArrayList;

public class CategoryMealsActivity extends AppCompatActivity implements CategoryMealsViewInterface {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
   CategoryMealsAdapter adapter;
    CategoryMealsPresenterInterface categoryMealsPresenterInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_meals);
        recyclerView = findViewById(R.id.recycler_category);
        categoryMealsPresenterInterface = new CategoryMealsPresenter(Repository.getInstance(MealsClient.getInstance() , ConcreteLocalSource.getInstance(this),this ), this, this);
        String category = getIntent().getStringExtra("category");
        Log.i("milad", "onCreate: " + category);
        categoryMealsPresenterInterface.getCategoryName(category);
        layoutManager = new LinearLayoutManager(this);
        adapter = new CategoryMealsAdapter(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }





    @Override
    public void showData(ArrayList<Meals> meals) {
        adapter.setList(meals);
        adapter.notifyDataSetChanged();
    }
}
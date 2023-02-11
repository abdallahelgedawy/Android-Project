package com.example.myapp.dailyMeal.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.myapp.R;
import com.example.myapp.dailyMeal.presenter.DailyMealPresenterInterface;
import com.example.myapp.favorite.view.Favorite_itemsActivity;
import com.example.myapp.model.Repository;
import com.example.myapp.model.Meals;
import com.example.myapp.network.MealsClient;
import com.example.myapp.search.view.SearchActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class DailyMealActivity extends AppCompatActivity implements  DailyMealsViewInterface {
    BottomNavigationView bottomNavigationView;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    DailyMealAdapter adapter;

    DailyMealPresenterInterface dailyMealPresenterInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_meal);
        dailyMealPresenterInterface = new DailyMealPresenter(Repository.getInstance(this , MealsClient.getInstance()),this,this);
        dailyMealPresenterInterface.getMeal();
        bottomNavigationView =findViewById(R.id.bottomNavigationView);
        recyclerView = findViewById(R.id.recycler_daily);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        adapter = new DailyMealAdapter(this );
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), DailyMealActivity.class));
                        return true;
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                          return true;
                    case R.id.saved:
                        startActivity(new Intent(getApplicationContext(), Favorite_itemsActivity.class));
                        return true;
                    case R.id.planed:
                        startActivity(new Intent(getApplicationContext(), com.example.myapp.planMeals.view.planActivity.class));
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public void showData(ArrayList<Meals> products) {
        adapter.setList(products);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void addToFav(Meals product) {

    }
}
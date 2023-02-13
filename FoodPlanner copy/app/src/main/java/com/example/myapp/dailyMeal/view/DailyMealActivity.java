package com.example.myapp.dailyMeal.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.example.myapp.R;
import com.example.myapp.dailyMeal.presenter.DailyMealPresenter;
import com.example.myapp.dailyMeal.presenter.DailyMealPresenterInterface;
import com.example.myapp.db.ConcreteLocalSource;
import com.example.myapp.favorite.presenter.FavMealPressenter;
import com.example.myapp.favorite.view.Favorite_itemsActivity;
import com.example.myapp.model.Repository;
import com.example.myapp.model.Meals;
import com.example.myapp.network.MealsClient;
import com.example.myapp.search.view.SearchActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;

public class DailyMealActivity extends AppCompatActivity implements  DailyMealsViewInterface ,OnClickFavorite {
    BottomNavigationView bottomNavigationView;
    RecyclerView recyclerView;

    RecyclerView rv2;
    LinearLayoutManager layoutManager;

    LinearLayoutManager lm2;
    DailyMealAdapter adapter;

    DailyMealAdapter ad2;
    FavMealPressenter favPressenter;
    DailyMealPresenterInterface dailyMealPresenterInterface;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_meal);

        dailyMealPresenterInterface = new DailyMealPresenter(Repository.getInstance(MealsClient.getInstance(), ConcreteLocalSource.getInstance(this), this), this, this);


        dailyMealPresenterInterface.getMeal();
        dailyMealPresenterInterface.getAllMeal();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        recyclerView = findViewById(R.id.recycler_daily);
        rv2 = findViewById(R.id.search_rec);

        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);

        lm2 = new LinearLayoutManager(this);
        lm2.setOrientation(RecyclerView.VERTICAL);

        adapter = new DailyMealAdapter(this, this);
        ad2 = new DailyMealAdapter(this ,this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        rv2.setLayoutManager(lm2);
        rv2.setAdapter(ad2);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
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
    public void showallData(ArrayList<Meals> products) {
        ad2.setList(products);
        ad2.notifyDataSetChanged();
    }

    @Override
    public void addToFav(Meals meals) {
        dailyMealPresenterInterface.addToFav(meals);

    }

    @Override
    public void deleteFav(Meals meals) {
        favPressenter.delete(meals);
    }

    @Override
    public void onClick(Meals meals) {
        addToFav(meals);
    }
}
package com.example.myapp.favorite.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.myapp.R;
import com.example.myapp.dailyMeal.view.DailyMealActivity;
import com.example.myapp.dailyMeal.view.OnClickFavorite;
import com.example.myapp.db.ConcreteLocalSource;
import com.example.myapp.favorite.presenter.FavMealPressenter;
import com.example.myapp.model.Meals;
import com.example.myapp.model.Repository;
import com.example.myapp.network.MealsClient;
import com.example.myapp.search.view.SearchActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Favorite_itemsActivity extends AppCompatActivity implements OnClickFavorite, FavMealsInterface    {
    RecyclerView recyclerView;
    FavoriteAdapter favoriteAdapter;
    FavMealPressenter favPressenter;
    LinearLayoutManager linearLayoutManager;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_items);

        recyclerView=findViewById(R.id.favorite_rec);

        linearLayoutManager = new LinearLayoutManager(this);
        favoriteAdapter = new FavoriteAdapter(Favorite_itemsActivity.this, new ArrayList<>(),Favorite_itemsActivity.this);
        favPressenter = new FavMealPressenter( Repository.getInstance(MealsClient.getInstance(), ConcreteLocalSource.getInstance(this), this),
                this,this);
        linearLayoutManager = new LinearLayoutManager(Favorite_itemsActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(favoriteAdapter);
        favPressenter.getFavMeals();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);


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
    public void showFavData(List<Meals> meals) {
        favoriteAdapter.setList(meals);
        favoriteAdapter.notifyDataSetChanged();
    }

    @Override
    public void deleteProduct(Meals meals) {
       favPressenter.delete(meals);
    }

    @Override
    public void onClick(Meals meals) {
         deleteProduct(meals);
    }

    @Override
    public void onClickDetails(String name) {

    }
}
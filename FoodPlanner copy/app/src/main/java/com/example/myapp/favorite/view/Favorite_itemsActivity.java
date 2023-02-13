package com.example.myapp.favorite.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapp.R;
import com.example.myapp.dailyMeal.view.OnClickFavorite;
import com.example.myapp.db.ConcreteLocalSource;
import com.example.myapp.favorite.presenter.FavMealPressenter;
import com.example.myapp.model.Meals;
import com.example.myapp.model.Repository;
import com.example.myapp.network.MealsClient;

import java.util.ArrayList;
import java.util.List;

public class Favorite_itemsActivity extends AppCompatActivity implements OnClickFavorite, FavMealsInterface    {
    RecyclerView recyclerView;
    FavoriteAdapter favoriteAdapter;
    FavMealPressenter favPressenter;
    LinearLayoutManager linearLayoutManager;

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
        favPressenter.delete(meals);

    }
}
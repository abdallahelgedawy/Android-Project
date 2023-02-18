package com.example.myapp.search.category.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.myapp.R;
import com.example.myapp.dailyMeal.view.DailyMealActivity;
import com.example.myapp.db.ConcreteLocalSource;
import com.example.myapp.detailedmeal.view.DetailedMealActivity;
import com.example.myapp.login.view.LoginActivity;
import com.example.myapp.model.Category;
import com.example.myapp.model.Meals;
import com.example.myapp.model.Repository;
import com.example.myapp.network.MealsClient;
import com.example.myapp.search.byIngredients.view.IngredientsActivity;
import com.example.myapp.search.byIngredients.view.OnClickFavoriteIngrediant;
import com.example.myapp.search.byIngredients.view.Search_by_ingredients_Activity;
import com.example.myapp.search.category.presenter.CategoryMealsPresenter;
import com.example.myapp.search.category.presenter.CategoryMealsPresenterInterface;
import com.example.myapp.search.category.presenter.CategoryPresenterInterface;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class CategoryMealsActivity extends AppCompatActivity implements CategoryMealsViewInterface , OnClickFavoritCategory {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
   CategoryMealsAdapter adapter;
    CategoryMealsPresenterInterface categoryMealsPresenterInterface;
    ImageView arrow;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_meals);
        recyclerView = findViewById(R.id.recycler_category);
        arrow=findViewById(R.id.arrow);
        categoryMealsPresenterInterface = new CategoryMealsPresenter(Repository.getInstance(MealsClient.getInstance() , ConcreteLocalSource.getInstance(this),this ), this, this);
        String category = getIntent().getStringExtra("category");
        Log.i("milad", "onCreate: " + category);
        categoryMealsPresenterInterface.getCategoryName(category);
        layoutManager = new LinearLayoutManager(this);
        adapter = new CategoryMealsAdapter(this,this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        firebaseAuth = FirebaseAuth.getInstance();

        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryMealsActivity.this, CategoryActivity.class);
                startActivity(intent);

            }
        });
    }





    @Override
    public void showData(ArrayList<Meals> meals) {
        adapter.setList(meals);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void addToFAv(Meals meals) {
        categoryMealsPresenterInterface.addToFav(meals);

    }

    @Override
    public void onClick(Meals meals) {
       addToFAv(meals);
    }
}
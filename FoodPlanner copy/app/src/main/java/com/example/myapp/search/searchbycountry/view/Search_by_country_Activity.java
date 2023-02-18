package com.example.myapp.search.searchbycountry.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.myapp.R;
import com.example.myapp.dailyMeal.view.DailyMealActivity;
import com.example.myapp.db.ConcreteLocalSource;
import com.example.myapp.detailedmeal.view.DetailedMealActivity;
import com.example.myapp.login.view.LoginActivity;
import com.example.myapp.model.Meals;
import com.example.myapp.model.Repository;
import com.example.myapp.network.MealsClient;
import com.example.myapp.search.byIngredients.view.IngredientsActivity;
import com.example.myapp.search.byIngredients.view.Search_by_ingredients_Activity;
import com.example.myapp.search.searchbycountry.presenter.CountryMealPresenter;
import com.example.myapp.search.searchbycountry.presenter.CountryMealPresenterInterface;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class Search_by_country_Activity extends AppCompatActivity implements CountryMealsViewInterface ,OnClickName{
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    CountryAdapter adapter;
    CountryMealPresenterInterface countryMealPresenterInterface;
    ImageView arrow;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_country);

        recyclerView = findViewById(R.id.country_rec);
        arrow=findViewById(R.id.arrow);

        countryMealPresenterInterface = new CountryMealPresenter(Repository.getInstance(MealsClient.getInstance(), ConcreteLocalSource.getInstance(this),this),this,this);
        String country = getIntent().getStringExtra("Country");
        countryMealPresenterInterface.getCountryMeal(country);

        layoutManager = new LinearLayoutManager(this);
        adapter = new CountryAdapter(this,this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        firebaseAuth = FirebaseAuth.getInstance();
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Search_by_country_Activity.this, CountryMealsActivity.class);
                startActivity(intent);

            }
        });
    }


    @Override
    public void showCountryData(ArrayList<Meals> listmael) {
        adapter.setList(listmael);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void addToFav(Meals meals) {
        countryMealPresenterInterface.addToFav(meals);
    }


    @Override
    public void onClick(Meals meals) {
       addToFav(meals);
    }


}
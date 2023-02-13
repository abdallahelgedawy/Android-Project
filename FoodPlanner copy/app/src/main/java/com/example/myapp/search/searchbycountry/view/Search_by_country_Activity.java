package com.example.myapp.search.searchbycountry.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.myapp.R;
import com.example.myapp.db.ConcreteLocalSource;
import com.example.myapp.model.Meals;
import com.example.myapp.model.Repository;
import com.example.myapp.network.MealsClient;
import com.example.myapp.search.searchbycountry.presenter.CountryMealPresenter;
import com.example.myapp.search.searchbycountry.presenter.CountryMealPresenterInterface;

import java.util.ArrayList;

public class Search_by_country_Activity extends AppCompatActivity implements CountryMealsViewInterface {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    CountryAdapter adapter;
    CountryMealPresenterInterface countryMealPresenterInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_country);

        recyclerView = findViewById(R.id.country_rec);

        countryMealPresenterInterface = new CountryMealPresenter(Repository.getInstance(MealsClient.getInstance(), ConcreteLocalSource.getInstance(this),this),this,this);
        String country = getIntent().getStringExtra("Country");
        countryMealPresenterInterface.getCountryMeal(country);

        layoutManager = new LinearLayoutManager(this);
        adapter = new CountryAdapter(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showCountryData(ArrayList<Meals> listmael) {
        adapter.setList(listmael);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void addToFav(Meals meals) {

    }


}
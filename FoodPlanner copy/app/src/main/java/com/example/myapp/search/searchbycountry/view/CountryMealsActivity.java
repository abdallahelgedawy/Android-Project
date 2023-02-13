package com.example.myapp.search.searchbycountry.view;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.myapp.R;
import com.example.myapp.model.Countrys;
import com.example.myapp.model.Meals;
import com.example.myapp.model.Repository;
import com.example.myapp.network.MealsClient;
import com.example.myapp.search.searchbycountry.presenter.CountryMealPresenter;
import com.example.myapp.search.searchbycountry.presenter.CountryMealPresenterInterface;

import java.util.ArrayList;

public class CountryMealsActivity extends AppCompatActivity implements OnClickCountry {
    RecyclerView recyclerView;

    ArrayList<Countrys> countrymeals = new ArrayList<Countrys>();
    CountryMealAdapter countryMealAdapter;
    // OnClickCountry listlener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_meals);

        recyclerView=findViewById(R.id.country_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        countrymeals.add(new Countrys(R.drawable.american,"American"));
        countrymeals.add(new Countrys(R.drawable.britain,"British"));
        countrymeals.add(new Countrys(R.drawable.canada, "Canadian"));
        countrymeals.add(new Countrys(R.drawable.china,"Chinese"));
        countrymeals.add(new Countrys(R.drawable.croatia,"Croatian"));
        countrymeals.add(new Countrys(R.drawable.dutch,"Dutch"));
        countrymeals.add(new Countrys(R.drawable.egypt,"Egyptian"));
        countrymeals.add(new Countrys(R.drawable.france,"French"));
        countrymeals.add(new Countrys(R.drawable.greece,"Greek"));
        countrymeals.add(new Countrys(R.drawable.india,"Indian"));
        countrymeals.add(new Countrys(R.drawable.ireland,"Irish"));
        countrymeals.add(new Countrys(R.drawable.italy,"Italian"));
        countrymeals.add(new Countrys(R.drawable.jamaica,"Jamaican"));
        countrymeals.add(new Countrys(R.drawable.japan,"Japanese"));
        countrymeals.add(new Countrys(R.drawable.kenya,"Kenyan"));
        countrymeals.add(new Countrys(R.drawable.malaysia,"Malaysian"));
        countrymeals.add(new Countrys(R.drawable.mexico,"Mexican"));
        countrymeals.add(new Countrys(R.drawable.morocco,"Moroccan"));
        countrymeals.add(new Countrys(R.drawable.poland,"Polish"));
        countrymeals.add(new Countrys(R.drawable.portugal,"Portuguese"));
        countrymeals.add(new Countrys(R.drawable.russia,"Russian"));
        countrymeals.add(new Countrys(R.drawable.spain,"Spanish"));
        countrymeals.add(new Countrys(R.drawable.thailand,"Thai"));
        countrymeals.add(new Countrys(R.drawable.tunisia,"Tunisian"));
        countrymeals.add(new Countrys(R.drawable.turkey,"Turkish"));
        countrymeals.add(new Countrys(R.drawable.unknown,"Unknown"));
        countrymeals.add(new Countrys(R.drawable.vietnam,"Vietnamese"));


            countryMealAdapter=new CountryMealAdapter(this ,countrymeals ,this);
            recyclerView.setAdapter(countryMealAdapter);
        }

    @Override
    public void onClick(String name) {
        Intent intent = new Intent(CountryMealsActivity.this , Search_by_country_Activity.class);
        intent.putExtra("Country" , name);
        Log.i("TAG", "onClick: " + name);
        startActivity(intent);
    }
}
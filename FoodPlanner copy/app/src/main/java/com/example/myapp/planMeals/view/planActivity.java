package com.example.myapp.planMeals.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.myapp.R;
import com.example.myapp.dailyMeal.view.DailyMealActivity;
import com.example.myapp.favorite.view.Favorite_itemsActivity;
import com.example.myapp.search.view.SearchActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.myapp.dailyMeal.view.DailyMealAdapter;
import com.example.myapp.db.ConcreteLocalSource;
import com.example.myapp.model.Meals;
import com.example.myapp.model.Repository;
import com.example.myapp.network.MealsClient;
import com.example.myapp.planMeals.presenter.PlanPresenter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class planActivity extends AppCompatActivity implements PlanViewInterface , OnClickRemove {
    ArrayList<Meals> item;
    ArrayList<Meals> itemsun;
    ArrayList<Meals> itemmon;
    ArrayList<Meals> itemtues;
    ArrayList<Meals> itemwed;
    ArrayList<Meals> itemthur;
    ArrayList<Meals> itemfrid;
    PlanPresenter presenter;
    BottomNavigationView bottomNavigationView;


    planAdapter adapter;
    planAdapter adapterSun;
    planAdapter adapterMon;
    planAdapter adapterTues;
    planAdapter adapterWed;
    planAdapter adapterThur;
    planAdapter adapterFrid;
    LinearLayoutManager layoutManager;
    LinearLayoutManager layoutManagersun;
    LinearLayoutManager layoutManagermon;
    LinearLayoutManager layoutManagertue;
    LinearLayoutManager layoutManagerwed;
    LinearLayoutManager layoutManagerthur;
    LinearLayoutManager layoutManagerfrid;



    RecyclerView recyclerView;
    RecyclerView recyclerViewsund;
    RecyclerView recyclerViewmond;
    RecyclerView recyclerViewtues;
    RecyclerView recyclerViewwed;
    RecyclerView recyclerViewthur;
    RecyclerView recyclerViewfri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
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

        recyclerView = findViewById(R.id.saturday);
        recyclerViewsund = findViewById(R.id.sunday);
        recyclerViewmond = findViewById(R.id.monday);
        recyclerViewtues = findViewById(R.id.tuesday);
        recyclerViewwed = findViewById(R.id.wednesday);
        recyclerViewthur = findViewById(R.id.thuresday);
        recyclerViewfri = findViewById(R.id.friday);
        item = new ArrayList<Meals>();
        itemsun = new ArrayList<Meals>();
        itemmon = new ArrayList<Meals>();
        itemtues = new ArrayList<Meals>();
        itemwed = new ArrayList<Meals>();
        itemthur = new ArrayList<Meals>();
        itemfrid = new ArrayList<Meals>();


        presenter =  new PlanPresenter(Repository.getInstance( MealsClient.getInstance() , ConcreteLocalSource.getInstance(this) , this) , this , this);
        presenter.getSaturday();
        adapter = new planAdapter(this , item ,this);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        presenter.getSunday();
        adapterSun = new planAdapter(this , itemsun , this);
        layoutManagersun = new LinearLayoutManager(this);
        recyclerViewsund.setLayoutManager(layoutManagersun);
        recyclerViewsund.setAdapter(adapterSun);
         presenter.getMonday();
         adapterMon = new planAdapter(this , itemmon ,  this);
        layoutManagermon = new LinearLayoutManager(this);
        recyclerViewmond.setLayoutManager(layoutManagermon);
        recyclerViewmond.setAdapter(adapterMon);
         presenter.getTuesday();
         adapterTues = new planAdapter(this , itemtues , this);
        layoutManagertue = new LinearLayoutManager(this);
        recyclerViewtues.setLayoutManager(layoutManagertue);
        recyclerViewtues.setAdapter(adapterTues);

         presenter.getWednesday();
         adapterWed = new planAdapter(this , itemwed ,  this);
        layoutManagerwed = new LinearLayoutManager(this);
        recyclerViewwed.setLayoutManager(layoutManagerwed);
        recyclerViewwed.setAdapter(adapterWed);

         presenter.getThursday();
         adapterThur = new planAdapter(this , itemthur , this);
        layoutManagerthur = new LinearLayoutManager(this);
        recyclerViewthur.setLayoutManager(layoutManagerthur);
        recyclerViewthur.setAdapter(adapterThur);

        presenter.getFriday();
         adapterFrid = new planAdapter(this , itemfrid , this);
        layoutManagerfrid = new LinearLayoutManager(this);
        recyclerViewfri.setLayoutManager(layoutManagerfrid);
        recyclerViewfri.setAdapter(adapterFrid);
    }
    public void showDataSaturday(Observable<List<Meals>> meals) {
         meals.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                 .subscribe(result -> {
                     for (int i = 0; i < result.size(); i++) {
                         item.add(result.get(i));
                     }
                     if (item.size()>0) {
                         adapter.setList(item);
                         adapter.notifyDataSetChanged();
                     }

                 });
     }

    @Override
    public void showDataSunday(Observable<List<Meals>> meals) {
        meals.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    for (int i = 0; i < result.size(); i++) {
                        itemsun.add(result.get(i));
                    }
                    if (itemsun.size()>0) {
                        adapterSun.setList(itemsun);
                        adapterSun.notifyDataSetChanged();
                    }


                });

    }

    @Override
    public void showDataMonday(Observable<List<Meals>> meals) {
        meals.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    for (int i = 0; i < result.size(); i++) {
                        itemmon.add(result.get(i));
                    }
                    if (itemmon.size()>0) {
                        adapterMon.setList(itemmon);
                        adapterMon.notifyDataSetChanged();
                    }



                });

    }

    @Override
    public void showDataTuesday(Observable<List<Meals>> meals) {
        meals.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    for (int i = 0; i < result.size(); i++) {
                        itemtues.add(result.get(i));
                    }
                    if (itemtues.size()>0) {
                        adapterTues.setList(itemtues);
                        adapterTues.notifyDataSetChanged();
                    }



                });

    }

    @Override
    public void showDataWednesday(Observable<List<Meals>> meals) {
        meals.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    for (int i = 0; i < result.size(); i++) {
                        itemwed.add(result.get(i));
                    }
                    if (itemwed.size() > 0) {
                        adapterWed.setList(itemwed);
                        adapterWed.notifyDataSetChanged();
                    }


                });

    }

    @Override
    public void showDataThursday(Observable<List<Meals>> meals) {
        meals.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    for (int i = 0; i < result.size(); i++) {
                        itemthur.add(result.get(i));
                    }
                    if (itemthur.size() > 0) {
                        adapterThur.setList(itemthur);
                        adapterThur.notifyDataSetChanged();
                    }


                });

    }

    @Override
    public void showDataFriday(Observable<List<Meals>> meals) {
        meals.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    for (int i = 0; i < result.size(); i++) {
                        itemfrid.add(result.get(i));
                    }
                    if (itemfrid.size() > 0) {
                        adapterFrid.setList(itemfrid);
                        adapterFrid.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void deleteMeal(Meals meals) {
    presenter.delete(meals);
    }

    @Override
    public void onClick(Meals meals) {
        deleteMeal(meals);
    }
}
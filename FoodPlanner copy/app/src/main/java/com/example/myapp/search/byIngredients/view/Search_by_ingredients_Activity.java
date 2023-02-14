package com.example.myapp.search.byIngredients.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapp.R;
import com.example.myapp.db.ConcreteLocalSource;
import com.example.myapp.model.Meals;
import com.example.myapp.model.Repository;
import com.example.myapp.network.MealsClient;
import com.example.myapp.search.byIngredients.presenter.SearchbyingPresenter;
import com.example.myapp.search.byIngredients.presenter.SearchbyingPresenterInterface;

import java.util.ArrayList;

public class Search_by_ingredients_Activity extends AppCompatActivity implements Search_by_ingredientsInterface {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    Search_by_ingredients_Adapter adapter;
    SearchbyingPresenterInterface searchbyingPresenterInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitysearchbyingredients);
        recyclerView = findViewById(R.id.recycler_ing);
        searchbyingPresenterInterface = new SearchbyingPresenter(Repository.getInstance(MealsClient.getInstance() , ConcreteLocalSource.getInstance(this),this ), this, this);
        String ing = getIntent().getStringExtra("ingredient");
        searchbyingPresenterInterface.getIngName(ing);
        layoutManager = new LinearLayoutManager(this);
        adapter = new Search_by_ingredients_Adapter(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void showData(ArrayList<Meals> meals) {
        adapter.setList(meals);
        adapter.notifyDataSetChanged();

    }
}
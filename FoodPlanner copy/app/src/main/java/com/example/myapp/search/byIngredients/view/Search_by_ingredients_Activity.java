package com.example.myapp.search.byIngredients.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.myapp.R;
import com.example.myapp.dailyMeal.view.DailyMealActivity;
import com.example.myapp.db.ConcreteLocalSource;
import com.example.myapp.login.view.LoginActivity;
import com.example.myapp.model.Meals;
import com.example.myapp.model.Repository;
import com.example.myapp.network.MealsClient;
import com.example.myapp.search.byIngredients.presenter.SearchbyingPresenter;
import com.example.myapp.search.byIngredients.presenter.SearchbyingPresenterInterface;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class Search_by_ingredients_Activity extends AppCompatActivity implements Search_by_ingredientsInterface,OnClickFavoriteIngrediant {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    Search_by_ingredients_Adapter adapter;
    SearchbyingPresenterInterface searchbyingPresenterInterface;

    ImageView arrow;

    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitysearchbyingredients);
        recyclerView = findViewById(R.id.recycler_ing);
        arrow=findViewById(R.id.arrow);
        searchbyingPresenterInterface = new SearchbyingPresenter(Repository.getInstance(MealsClient.getInstance() , ConcreteLocalSource.getInstance(this),this ), this, this);
        String ing = getIntent().getStringExtra("ingredient");
        searchbyingPresenterInterface.getIngName(ing);
        layoutManager = new LinearLayoutManager(this);
        adapter = new Search_by_ingredients_Adapter(this,this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        firebaseAuth = FirebaseAuth.getInstance();
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Search_by_ingredients_Activity.this, IngredientsActivity.class);
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
        searchbyingPresenterInterface.addToFav(meals);

    }

    @Override
    public void onClick(Meals meals) {
        addToFAv(meals);
    }
}
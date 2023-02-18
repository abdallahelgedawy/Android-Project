package com.example.myapp.search.byIngredients.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.myapp.R;
// import com.example.myapp.model.Category;
import com.example.myapp.dailyMeal.view.DailyMealActivity;
import com.example.myapp.db.ConcreteLocalSource;
import com.example.myapp.db.LocalSource;
import com.example.myapp.login.view.LoginActivity;
import com.example.myapp.model.Meals;
import com.example.myapp.model.Repository;
import com.example.myapp.network.MealsClient;
import com.example.myapp.search.byIngredients.presenter.IngredientsPresenter;
import com.example.myapp.search.byIngredients.presenter.IngredientsPresenterInterface;
import com.example.myapp.search.category.view.CategoryActivity;
import com.example.myapp.search.category.view.CategoryAdapter;
import com.example.myapp.search.category.view.CategoryMealsActivity;
import com.example.myapp.search.category.view.CategoryViewInterface;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;



public class IngredientsActivity extends AppCompatActivity implements IngredientsViewInterface , OnClickIngredients{
RecyclerView recyclerView;
LinearLayoutManager layoutManager;
IngredientsAdapter adapter;
IngredientsPresenterInterface ingredientsPresenterInterface;
LocalSource localSource;
private FirebaseAuth mAuth;
FirebaseAuth firebaseAuth;
ImageView arrow;
SearchView search;
EditText search_ingred;


    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);
        recyclerView = findViewById(R.id.recycle_ingred);
        ingredientsPresenterInterface = new IngredientsPresenter(Repository.getInstance(MealsClient.getInstance() , ConcreteLocalSource.getInstance(this),this ), this, this);
        ingredientsPresenterInterface.getIngredients();
        layoutManager = new LinearLayoutManager(this);
        adapter = new IngredientsAdapter(this, this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
         arrow=findViewById(R.id.arrow);

        firebaseAuth = FirebaseAuth.getInstance();

        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IngredientsActivity.this,com.example.myapp.search.view.SearchActivity.class);
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
    public void onClickIngredients(String name) {
        Intent intent = new Intent(IngredientsActivity.this , Search_by_ingredients_Activity.class);
        intent.putExtra("ingredient" , name);
        startActivity(intent);
    }
}
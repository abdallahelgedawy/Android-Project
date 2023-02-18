package com.example.myapp.search.category.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.myapp.R;
import com.example.myapp.dailyMeal.presenter.DailyMealPresenter;
import com.example.myapp.dailyMeal.presenter.DailyMealPresenterInterface;
import com.example.myapp.dailyMeal.view.DailyMealActivity;
import com.example.myapp.dailyMeal.view.DailyMealAdapter;
import com.example.myapp.db.ConcreteLocalSource;
import com.example.myapp.login.view.LoginActivity;
import com.example.myapp.model.Category;
import com.example.myapp.model.Repository;
import com.example.myapp.network.MealsClient;
import com.example.myapp.search.byIngredients.view.IngredientsActivity;
import com.example.myapp.search.category.presenter.CategoryPresenter;
import com.example.myapp.search.category.presenter.CategoryPresenterInterface;
import com.example.myapp.search.view.SearchActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity implements CategoryViewInterface , OnClickCategory{
    RecyclerView recyclerView;
LinearLayoutManager layoutManager;
    CategoryAdapter adapter;
    CategoryPresenterInterface categoryPresenterInterface;
    ImageView arrow;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        recyclerView = findViewById(R.id.recycler_category);
        categoryPresenterInterface = new CategoryPresenter(Repository.getInstance(MealsClient.getInstance() , ConcreteLocalSource.getInstance(this),this ), this, this);
       categoryPresenterInterface.getCategory();
        layoutManager = new LinearLayoutManager(this);
        adapter = new CategoryAdapter(this , this );
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        arrow=findViewById(R.id.arrow);
        firebaseAuth = FirebaseAuth.getInstance();
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, com.example.myapp.search.view.SearchActivity.class);
                startActivity(intent);
            }
        });
    }



    @Override
    public void showData(ArrayList<Category> meals) {
        adapter.setList(meals);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onClickCategry(String name) {
        Intent intent = new Intent(CategoryActivity.this , CategoryMealsActivity.class);
       intent.putExtra("category" , name);
        Log.i("TAG", "onClick: " + name);
        startActivity(intent);

    }
}
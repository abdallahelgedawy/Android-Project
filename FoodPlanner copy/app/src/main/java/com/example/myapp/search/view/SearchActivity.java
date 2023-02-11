package com.example.myapp.search.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapp.R;
import com.example.myapp.search.category.view.CategoryMealsActivity;
import com.example.myapp.search.searchbycountry.view.Search_by_country_Activity;
import com.example.myapp.search.byIngredients.view.IngredientsActivity;

public class SearchActivity extends AppCompatActivity {
Button btn1;
Button btn2;
Button btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        btn1 = findViewById(R.id.btn_country);
        btn2 = findViewById(R.id.btn_category);
        btn3 = findViewById(R.id.btn_ingredients);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(com.example.myapp.search.view.SearchActivity.this, Search_by_country_Activity.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(com.example.myapp.search.view.SearchActivity.this, CategoryMealsActivity.class);
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(com.example.myapp.search.view.SearchActivity.this, IngredientsActivity.class);
                startActivity(intent);

            }
        });
    }
}
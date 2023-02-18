package com.example.myapp.search.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.myapp.R;
import com.example.myapp.dailyMeal.view.DailyMealActivity;
import com.example.myapp.favorite.view.Favorite_itemsActivity;
import com.example.myapp.search.byIngredients.view.Search_by_ingredients_Activity;
import com.example.myapp.search.category.view.CategoryActivity;
import com.example.myapp.search.category.view.CategoryMealsActivity;
import com.example.myapp.search.searchbycountry.view.CountryMealsActivity;
import com.example.myapp.search.searchbycountry.view.Search_by_country_Activity;
import com.example.myapp.search.byIngredients.view.IngredientsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SearchActivity extends AppCompatActivity implements OnClick{
   ImageView btn1;
    ImageView btn2;
    ImageView btn3;

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        btn1 = findViewById(R.id.btn_country);
        btn2 = findViewById(R.id.btn_category);
        btn3 = findViewById(R.id.btn_ingredients);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(com.example.myapp.search.view.SearchActivity.this, CountryMealsActivity.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(com.example.myapp.search.view.SearchActivity.this, CategoryActivity.class);
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

    }

    @Override
    public void onClickIntegraite(String name) {
        Intent intent = new Intent(SearchActivity.this , IngredientsActivity.class);
        intent.putExtra("ingredient" , name);
        startActivity(intent);
    }

    @Override
    public void onClickCategory(String name) {
        Intent intent = new Intent(SearchActivity.this , CategoryActivity.class);
        intent.putExtra("Category" , name);
        startActivity(intent);

    }

    @Override
    public void onClickCountry(String name) {
        Intent intent = new Intent(SearchActivity.this , CountryMealsActivity.class);
        intent.putExtra("Country" , name);
        startActivity(intent);

    }
}

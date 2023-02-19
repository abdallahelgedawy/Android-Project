package com.example.myapp.dailyMeal.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.myapp.R;
import com.example.myapp.dailyMeal.presenter.DailyMealPresenter;
import com.example.myapp.dailyMeal.presenter.DailyMealPresenterInterface;
import com.example.myapp.db.ConcreteLocalSource;
import com.example.myapp.detailedmeal.view.DetailedMealActivity;
import com.example.myapp.favorite.presenter.FavMealPressenter;
import com.example.myapp.favorite.view.Favorite_itemsActivity;
import com.example.myapp.login.view.LoginActivity;
import com.example.myapp.model.Repository;
import com.example.myapp.model.Meals;
import com.example.myapp.network.MealsClient;
import com.example.myapp.registration.view.RegistrationActivity;
import com.example.myapp.search.view.SearchActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import io.reactivex.Observable;

public class DailyMealActivity extends AppCompatActivity implements  DailyMealsViewInterface ,OnClickFavorite {

    DailyMealAdapter dailyAdapter;

    BottomNavigationView bottomNavigationView;
    RecyclerView recyclerView;

    RecyclerView rv2;
    LinearLayoutManager layoutManager;

    LinearLayoutManager lm2;
    DailyMealAdapter adapter;

    DailyMealAdapter ad2;
    FavMealPressenter favPressenter;
    DailyMealPresenterInterface dailyMealPresenterInterface;
    private ArrayList<String> days = new ArrayList<>();
    EditText search;

    ImageView arrow;


    FirebaseAuth firebaseAuth;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_meal);
        days.add("               Choose the day");
        days.add("Saturday");
        days.add("Sunday");
        days.add("Monday");
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");

        firebaseAuth = FirebaseAuth.getInstance();


        search = findViewById(R.id.searchtxt);
        dailyMealPresenterInterface = new DailyMealPresenter(Repository.getInstance(MealsClient.getInstance(), ConcreteLocalSource.getInstance(this), this), this, this);
        dailyMealPresenterInterface.getMeal();
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length()!=0) {
                        dailyMealPresenterInterface.getAllMeal(charSequence.toString());
                }
                else if (charSequence.length()==0) {
                    ad2.setList(new ArrayList<>());
                    ad2.notifyDataSetChanged();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });



        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        recyclerView = findViewById(R.id.recycler_daily);
        rv2 = findViewById(R.id.search_rec);

        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);

        lm2 = new LinearLayoutManager(this);
        lm2.setOrientation(RecyclerView.VERTICAL);

        adapter = new DailyMealAdapter(this, this , days);
        ad2 = new DailyMealAdapter (this ,this , days);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        rv2.setLayoutManager(lm2);
        rv2.setAdapter(ad2);

        arrow=findViewById(R.id.arrow);

        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DailyMealActivity.this, LoginActivity.class));
                firebaseAuth.signOut();
                dailyMealPresenterInterface.deleteallMeals();
                finish();
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
    public void showData(ArrayList<Meals> products) {
        adapter.setList(products);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void showallData(ArrayList<Meals> products) {
        if (products!=null){
            ad2.setList(products);
            ad2.notifyDataSetChanged();

        }
        else {
            Toast.makeText(this, "No Data Available", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void addToFav(Meals meals) {
        dailyMealPresenterInterface.addToFav(meals);

    }

    @Override
    public void deleteFav(Meals meals) {
        favPressenter.delete(meals);
    }

    @Override
    public void onClick(Meals meals) {
        addToFav(meals);
    }

    @Override
    public void onClickDetails(String name) {
        Intent intent = new Intent(this , DetailedMealActivity.class);
        intent.putExtra("category" , name);
        startActivity(intent);
    }
}
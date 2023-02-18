package com.example.myapp.detailedmeal.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.myapp.R;
import com.example.myapp.db.ConcreteLocalSource;
import com.example.myapp.detailedmeal.Presenter.DetailsPresenter;
import com.example.myapp.detailedmeal.Presenter.DetailsPresenterInterface;
import com.example.myapp.model.Meals;
import com.example.myapp.model.Repository;
import com.example.myapp.network.MealsClient;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class DetailedMealActivity extends AppCompatActivity  implements DetailedviewInteface{
    TextView name;
    TextView country;
    TextView description;
    ImageView img;
    ToggleButton fav;
     TextView steps;
    String  url;
     VideoView video;
     DetailsPresenterInterface presenter;
     Meals mealDetails;
     DetailedAdapter adapter;
     LinearLayoutManager layout;
     RecyclerView recyclerView;
    ArrayList<String> ingMeal = new ArrayList<>();
    YouTubePlayerView youtube;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_meal);
        name = findViewById(R.id.txt_Details);
        country =findViewById(R.id.txt_country);
        description = findViewById(R.id.txt_desc);
        img = findViewById(R.id.img);
        steps = findViewById(R.id.steps);
        fav = findViewById(R.id.btn_fav);
        youtube = findViewById(R.id.ybv);
        recyclerView = findViewById(R.id.recycle_details);
        String ing = getIntent().getStringExtra("category");
        Log.i("details", "onCreate: " + ing);
        presenter = new DetailsPresenter(this , this , Repository.getInstance(MealsClient.getInstance() , ConcreteLocalSource.getInstance(this),this ));
        presenter.getName(ing);
        adapter = new DetailedAdapter(this );
        layout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layout);
        recyclerView.setAdapter(adapter);

        getLifecycle().addObserver(youtube);

    }

    @Override
    public void showData(ArrayList<Meals> meals) {
        name.setText(meals.get(0).getStrMeal());
        country.setText(meals.get(0).getStrArea());
        description.setText(meals.get(0).getStrTags());
        Glide.with(this).load(meals.get(0).getStrMealThumb()).into(img);
        steps.setText(meals.get(0).getStrInstructions());
        Log.i("TAG", "showData: " + meals.get(0).getStrMeal());
        MealIng(meals.get(0));
        url = meals.get(0).getStrYoutube();
        youtube.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                if( url!=null){
                    url = url.substring(url.indexOf("=") + 1);
                    StringTokenizer st = new StringTokenizer(url, "&");
                    url = st.nextToken();
                    youTubePlayer.loadVideo(url, 0);
                    youTubePlayer.pause();
                }
            }
            });
    }

    public void MealIng(Meals meals){
        Log.i("TAG", "MealIng: " + meals.getStrIngredient1());
        if (meals.getStrIngredient1()!=null && !meals.getStrIngredient1().isEmpty())
            ingMeal.add(meals.getStrIngredient1());
            Log.i("TAG", "MealIng: " + ingMeal);
        if (meals.getStrIngredient2()!=null && !meals.getStrIngredient2().isEmpty())
            ingMeal.add(meals.getStrIngredient2());
        if (meals.getStrIngredient3()!=null && !meals.getStrIngredient3().isEmpty())
            ingMeal.add(meals.getStrIngredient3());
        if (meals.getStrIngredient4()!=null && !meals.getStrIngredient4().isEmpty())
            ingMeal.add(meals.getStrIngredient4());
        if (meals.getStrIngredient5()!=null && !meals.getStrIngredient5().isEmpty())
            ingMeal.add(meals.getStrIngredient5());
        if (meals.getStrIngredient6()!=null && !meals.getStrIngredient6().isEmpty())
            ingMeal.add(meals.getStrIngredient6());
        if (meals.getStrIngredient7()!=null && !meals.getStrIngredient7().isEmpty())
            ingMeal.add(meals.getStrIngredient7());
        if (meals.getStrIngredient8()!=null && !meals.getStrIngredient8().isEmpty())
            ingMeal.add(meals.getStrIngredient8());
        if (meals.getStrIngredient9()!=null && !meals.getStrIngredient9().isEmpty())
            ingMeal.add(meals.getStrIngredient9());
            if (meals.getStrIngredient10()!=null && !meals.getStrIngredient10().isEmpty())
                ingMeal.add(meals.getStrIngredient10());
            if (meals.getStrIngredient11()!=null && !meals.getStrIngredient11().isEmpty())
                ingMeal.add(meals.getStrIngredient11());
            if (meals.getStrIngredient12()!=null && !meals.getStrIngredient12().isEmpty())
                ingMeal.add(meals.getStrIngredient12());
            if (meals.getStrIngredient13()!=null && !meals.getStrIngredient13().isEmpty())
                ingMeal.add(meals.getStrIngredient13());
            if (meals.getStrIngredient14()!=null && !meals.getStrIngredient14().isEmpty())
                ingMeal.add(meals.getStrIngredient14());
            if (meals.getStrIngredient15()!=null && !meals.getStrIngredient15().isEmpty())
                ingMeal.add(meals.getStrIngredient15());
            adapter.setList(ingMeal);
            adapter.notifyDataSetChanged();
    }


}
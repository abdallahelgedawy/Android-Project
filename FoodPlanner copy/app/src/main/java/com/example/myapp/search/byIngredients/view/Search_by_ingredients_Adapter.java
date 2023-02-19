package com.example.myapp.search.byIngredients.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapp.R;
import com.example.myapp.dailyMeal.view.OnClickFavorite;
import com.example.myapp.model.Meals;
import com.example.myapp.network.FirebaseUsers;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class Search_by_ingredients_Adapter extends RecyclerView.Adapter<Search_by_ingredients_Adapter.viewHolder> {
    private Context context;
    private ArrayList<Meals> meals;

    private boolean clicked = false;
    FirebaseAuth auth;
    FirebaseUser user;
    ArrayList<String> days = new ArrayList<>();

    OnClickFavoriteIngrediant listener;
    public Search_by_ingredients_Adapter(Context context,OnClickFavoriteIngrediant listener , ArrayList<String> days) {
        this.context = context;
        this.meals = new ArrayList<>();
        this. listener=listener;
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        this.days = days;
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater  = (LayoutInflater) context .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.ingredients , parent , false);
        viewHolder vh = new viewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Search_by_ingredients_Adapter.viewHolder holder, int position) {
        Meals meal = meals.get(position);
        holder.title.setText(meal.getStrMeal());
        Glide.with(context).load(meal.getStrMealThumb()).into(holder.img);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context.getApplicationContext(), android.R.layout.simple_spinner_item,days );
        holder.days.setAdapter(arrayAdapter);
        holder.days.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 1:
                        meal.setDay("1");
                        listener.onClick(meal);
                        FirebaseUsers.addToPlanfire(context , meal);
                        break;
                    case 2:
                        meal.setDay("2");
                        listener.onClick(meal);
                        FirebaseUsers.addToPlanfire(context , meal);
                        break;
                    case 3:
                        meal.setDay("3");
                        listener.onClick(meal);
                        FirebaseUsers.addToPlanfire(context , meal);
                        break;
                    case 4:
                        meal.setDay("4");
                        listener.onClick(meal);
                        FirebaseUsers.addToPlanfire(context , meal);
                        break;
                    case 5:
                        meal.setDay("5");
                        listener.onClick(meal);
                        FirebaseUsers.addToPlanfire(context , meal);
                        break;
                    case 6:
                        meal.setDay("6");
                        listener.onClick(meal);
                        FirebaseUsers.addToPlanfire(context , meal);
                        break;
                    case 7:
                        meal.setDay("7");
                        listener.onClick(meal);
                        FirebaseUsers.addToPlanfire(context , meal);
                        break;


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickDetails(holder.title.getText().toString());
            }
        });
        holder.fav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (user != null) {
                   listener.onClick(meal);
                    if (!clicked) {
                        holder.fav.setBackgroundResource(R.drawable.baseline_red_24);
                        meal.setDay("0");
                        listener.onClick(meal);
                        FirebaseUsers.addToFavoritefire(context , meal);
                        Toast.makeText(context.getApplicationContext(), "added", Toast.LENGTH_SHORT).show();
                        clicked = true;
                    }
                }else{
                    Toast.makeText(context, "You Must Login", Toast.LENGTH_SHORT).show();
                }

            }

        });

    }
    public void setList(ArrayList<Meals> meals) {
        this.meals = meals;

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }
    public static class viewHolder extends RecyclerView.ViewHolder{

        TextView title;
        ImageView img;
        ToggleButton fav;
        Spinner days;
        CardView card;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_cat);
            img = itemView.findViewById(R.id.img);
            fav = itemView.findViewById(R.id.fav_cat);
            days = itemView.findViewById(R.id.plan);
            card = itemView.findViewById(R.id.card);


        }
    }

}

package com.example.myapp.dailyMeal.view;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.R;
import com.example.myapp.model.DailyMeals;
import com.example.myapp.model.Meals;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapp.model.RandomMeals;


import java.util.ArrayList;
import java.util.List;

public class DailyMealAdapter extends RecyclerView.Adapter<DailyMealAdapter.viewHolder> {
    private Context context;
    private ArrayList<Meals> meals;

    private  static  boolean clicked = false;




    public DailyMealAdapter(Context context) {
        this.context = context;
        this.meals = new ArrayList<>();
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater  = (LayoutInflater) context .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_daily_meal , parent , false);
        viewHolder vh = new viewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Meals meal = meals.get(position);

        holder.title.setText(meal.getStrMeal());
        Glide.with(context).load(meal.getStrMealThumb()).into(holder.img);
       holder.fav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

               if (!clicked) {
                   holder.fav.setBackgroundResource(R.drawable.baseline_red_24);
                   clicked = true;

               }

              else if(clicked){
                       holder.fav.setBackgroundResource(R.drawable.baseline_favorite_24);
                       clicked = false;

                   }
               }

       });

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }
    public void setList(ArrayList<Meals>meals){
        this.meals = meals;
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView title;
        ImageView img;
       ToggleButton  fav;
       Spinner days;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
         title = itemView.findViewById(R.id.textView3);
         img = itemView.findViewById(R.id.imageView5);
         fav = itemView.findViewById(R.id.btn_fav);
         days = itemView.findViewById(R.id.spinner);
        }
    }
}

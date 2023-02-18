package com.example.myapp.dailyMeal.view;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.R;
import com.example.myapp.model.Meals;
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
import android.widget.ToggleButton;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;


import java.util.ArrayList;

public class DailyMealAdapter extends RecyclerView.Adapter<DailyMealAdapter.viewHolder> {
    private Context context;
    private ArrayList<Meals> meals;
     private OnClickFavorite listener;
    private  static  boolean clicked = false;
    ArrayList<String> days = new ArrayList<>();






    public DailyMealAdapter(Context context ,OnClickFavorite listener , ArrayList<String> days) {
        this.context = context;
        this.listener=listener;
        this.meals = new ArrayList<>();
        this.days = days;

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
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context.getApplicationContext(), android.R.layout.simple_spinner_item, days);
        holder.plan.setAdapter(arrayAdapter);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickDetails(holder.title.getText().toString());
            }
        });
        holder.plan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 1 :
                        meal.setDay("1");
                        listener.onClick(meal);
                    case 2 :
                        meal.setDay("2");
                        listener.onClick(meal);
                    case 3:
                        meal.setDay("3");
                        listener.onClick(meal);
                    case 4 :
                        meal.setDay("4");
                        listener.onClick(meal);
                    case 5 :
                        meal.setDay("5");
                        listener.onClick(meal);
                    case 6 :
                        meal.setDay("6");
                        listener.onClick(meal);
                    case 7 :
                        meal.setDay("7");
                        listener.onClick(meal);


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
       holder.fav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
               meal.setDay("0");
               listener.onClick(meal);

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
       ConstraintLayout layout;


        Spinner plan;




        public viewHolder(@NonNull View itemView) {
            super(itemView);
         title = itemView.findViewById(R.id.text_cat);
         img = itemView.findViewById(R.id.img_details);
         fav = itemView.findViewById(R.id.btn_fav);
         plan = itemView.findViewById(R.id.spinner_plan);
         layout = itemView.findViewById(R.id.layout_details);

        }
    }
}

package com.example.myapp.favorite.view;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapp.R;
import com.example.myapp.dailyMeal.view.OnClickFavorite;
import com.example.myapp.model.Meals;
import com.google.android.material.snackbar.Snackbar;


import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.viewHolder> {
    private Context context;
    private List<Meals> meals;
    private OnClickFavorite listener;
    private boolean clicked = false;

    public FavoriteAdapter(Context context, List<Meals> meals, OnClickFavorite listener) {
        this.context = context;
        this.meals = meals;
        this.listener = listener;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater  = (LayoutInflater) context .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_favorite , parent , false);
        viewHolder vh = new viewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Meals meal = meals.get(position);

        holder.title.setText(meal.getStrMeal());

        Glide.with(context).load(meal.getStrMealThumb()).into(holder.img);


        holder.rmv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(meal);
                meals.remove(position);
                notifyDataSetChanged();
            }
        });





    }

    @Override
    public int getItemCount() {
        return meals.size();
    }
    public void setList(List<Meals>products){
        this.meals = products;
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView description;
        ImageView img;
        Button rmv;
        ToggleButton favorite;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.fav_name);
            img = itemView.findViewById(R.id.fav_img);
            favorite = itemView.findViewById(R.id.fav_cat);
            rmv=itemView.findViewById(R.id.button);
        }
    }
}


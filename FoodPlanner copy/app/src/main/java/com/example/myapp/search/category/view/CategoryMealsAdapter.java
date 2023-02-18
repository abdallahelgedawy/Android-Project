package com.example.myapp.search.category.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapp.R;
import com.example.myapp.model.Meals;

import java.util.ArrayList;

public class CategoryMealsAdapter extends RecyclerView.Adapter<CategoryMealsAdapter.viewHolder> {
        private Context context;
        private ArrayList<Meals> meals;
       private boolean clicked = false;

        public CategoryMealsAdapter(Context context) {
            this.context = context;
            this.meals = new ArrayList<>();
        }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater  = (LayoutInflater) context .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.category_meals , parent , false);
        viewHolder vh = new viewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Meals meal = meals.get(position);
        holder.title.setText(meal.getStrMeal());
        Glide.with(context).load(meal.getStrMealThumb()).into(holder.img);
        holder.favorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (!clicked) {
                    holder.favorite.setBackgroundResource(R.drawable.baseline_red_24);
                    clicked = true;

                } else if (clicked) {
                    holder.favorite.setBackgroundResource(R.drawable.baseline_favorite_24);
                    clicked = false;

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



        public class viewHolder extends RecyclerView.ViewHolder {

            TextView title;
            ImageView img;
            ToggleButton favorite;
            Spinner days;

            public viewHolder(@NonNull View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.text_cat);
                img = itemView.findViewById(R.id.img_details);
                favorite = itemView.findViewById(R.id.fav_details);
               // days = itemView.findViewById(R.id.spinner_category);
            }
        }
    }

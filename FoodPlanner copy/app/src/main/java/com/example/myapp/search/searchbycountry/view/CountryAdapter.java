package com.example.myapp.search.searchbycountry.view;

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

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.viewHolder>{
    private Context context;
    private ArrayList<Meals> meals;
    private boolean clicked = false;

    public CountryAdapter(Context context) {
        this.context = context;
        this.meals = new ArrayList<>();
    }
    @NonNull
    @Override
    public CountryAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater  = (LayoutInflater) context .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.country_meals , parent , false);
        viewHolder vh = new viewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.viewHolder holder, int position) {
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

    @Override
    public int getItemCount() {

        return meals.size();
    }
    public void setList(ArrayList<Meals> meals) {
        this.meals = meals;

    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView img;
        ToggleButton favorite;
        Spinner days;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_cat);
            img = itemView.findViewById(R.id.img);
            favorite = itemView.findViewById(R.id.fav_cat);
            days = itemView.findViewById(R.id.spinner);
        }
    }
}

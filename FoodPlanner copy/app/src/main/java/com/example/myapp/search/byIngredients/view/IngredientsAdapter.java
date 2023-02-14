package com.example.myapp.search.byIngredients.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapp.R;
import com.example.myapp.model.Meals;

import java.util.ArrayList;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.viewHolder> {
    private Context context;
    private ArrayList<Meals> meals;
    private String url_part1="https://www.themealdb.com/images/ingredients/";
    private  String url_part2=".png";
    private OnClickIngredients click;
    public IngredientsAdapter(Context context , OnClickIngredients click){
        this.context = context;
        this.meals = new ArrayList<>();
        this.click = click;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater  = (LayoutInflater) context .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.searchbyingredients, parent , false);
        viewHolder vh = new viewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Meals meal = meals.get(position);
        holder.title.setText(meal.getStrIngredient());
        Glide.with(context).load(url_part1+meal.getStrIngredient()+url_part2).into(holder.img);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.onClick(holder.title.getText().toString());
            }
        });

    }
    public void filterList(ArrayList<Meals> filterlist) {
        meals = filterlist;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
      return meals.size();
    }
    public void setList(ArrayList<Meals> meals){
        this.meals = meals;
    }
    public static class viewHolder extends RecyclerView.ViewHolder{

        TextView title;
        ImageView img;
       ConstraintLayout layout;



        public viewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_ing);
            img = itemView.findViewById(R.id.img_ing);
           layout = itemView.findViewById(R.id.layout_ing);


        }
    }
}


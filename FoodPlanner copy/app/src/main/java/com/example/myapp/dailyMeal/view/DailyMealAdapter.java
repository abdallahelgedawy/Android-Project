package com.example.myapp.dailyMeal.view;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.R;
import com.example.myapp.model.Meals;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.util.ArrayList;

public class DailyMealAdapter extends RecyclerView.Adapter<DailyMealAdapter.viewHolder> {
    private Context context;
    private ArrayList<Meals> meals;
     private OnClickFavorite listener;
    private  static  boolean clicked = false;
    FirebaseAuth auth;
    FirebaseUser user;


    public DailyMealAdapter(Context context ,OnClickFavorite listener) {
        this.context = context;
        this.listener=listener;
        this.meals = new ArrayList<>();
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
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
               if (user != null) {
                   listener.onClick(meal);
                   if (!clicked) {
                       holder.fav.setBackgroundResource(R.drawable.baseline_red_24);
                       clicked = true;
                   } else if (clicked) {
                       holder.fav.setBackgroundResource(R.drawable.baseline_favorite_24);
                       clicked = false;
                   }
               }else{
                   Toast.makeText(context, "You Must Login", Toast.LENGTH_SHORT).show();
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


        public viewHolder(@NonNull View itemView) {
            super(itemView);
         title = itemView.findViewById(R.id.text_search);
         img = itemView.findViewById(R.id.img_search);
         fav = itemView.findViewById(R.id.btn_fav);

        }
    }
}

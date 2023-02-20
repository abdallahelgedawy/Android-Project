package com.example.myapp.planMeals.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapp.R;
import com.example.myapp.dailyMeal.view.OnClickFavorite;
import com.example.myapp.model.Meals;
import com.example.myapp.network.FirebaseUsers;


import java.util.List;

public class planAdapter extends RecyclerView.Adapter<planAdapter.viewHolder> {
    private Context context;
    private List<Meals> meals;

    private boolean clicked = false;
    OnClickRemove listener;

    public planAdapter(Context context, List<Meals> meals,OnClickRemove listener ) {
        this.context = context;
        this.meals = meals;
        this.listener = listener;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater  = (LayoutInflater) context .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.plan , parent , false);
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
                FirebaseUsers.removePlanFromFirebase(context,meal);
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
        ImageView img;
        Button rmv;



        public viewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tv_plan);
            img = itemView.findViewById(R.id.img_plan);
            rmv=itemView.findViewById(R.id.rmv_plan);
        }
    }
}


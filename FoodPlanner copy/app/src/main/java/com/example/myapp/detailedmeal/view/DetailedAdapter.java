package com.example.myapp.detailedmeal.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapp.R;
import com.example.myapp.model.Meals;

import java.util.ArrayList;

public class DetailedAdapter extends RecyclerView.Adapter<DetailedAdapter.viewHolder> {
        private Context context;
        private ArrayList<String> meal;



        public DetailedAdapter(Context context) {
            this.context = context;
           this.meal = new ArrayList<>();
        }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater  = (LayoutInflater) context .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.details , parent , false);
        viewHolder vh = new viewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        String ing = meal.get(position);
        holder.ing.setText(ing);
    }

    @Override
            public int getItemCount () {

                return meal.size();
            }
            public void setList (ArrayList<String> meals) {
               this.meal = meals;
            }
        public static class viewHolder extends RecyclerView.ViewHolder{
            TextView ing;
            public viewHolder(@NonNull View itemView) {
                super(itemView);
                ing = itemView.findViewById(R.id.Details_txt);
            }
        }
    }



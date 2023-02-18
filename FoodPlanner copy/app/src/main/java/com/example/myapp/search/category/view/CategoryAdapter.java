package com.example.myapp.search.category.view;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.R;
import com.example.myapp.model.Category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;


import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewHolder> {
    private Context context;
    private ArrayList<Category> category;
    private OnClickCategory listener;
   // private boolean clicked = false;

    public CategoryAdapter(Context context , OnClickCategory listener) {
        this.context = context;
        this.category = new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater  = (LayoutInflater) context .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.category , parent , false);
        viewHolder vh = new viewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Category category1 = category.get(position);
        holder.title.setText(category1.getStrCategory());
        Glide.with(context).load(category1.getStrCategoryThumb()).into(holder.img);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickCategry(holder.title.getText().toString());
            }
        });



    }

    @Override
    public int getItemCount() {
        return category.size();
    }
    public void setList(ArrayList<Category>meals){
        this.category = meals;
    }

    public static class viewHolder extends RecyclerView.ViewHolder{

        TextView title;
        ImageView img;
        LinearLayout layout;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_cat);
            img = itemView.findViewById(R.id.img);
            layout = itemView.findViewById(R.id.layout_category);

        }
    }
}

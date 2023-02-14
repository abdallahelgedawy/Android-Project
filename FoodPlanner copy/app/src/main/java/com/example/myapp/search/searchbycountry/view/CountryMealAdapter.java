package com.example.myapp.search.searchbycountry.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapp.R;
import com.example.myapp.model.Countrys;

import java.util.ArrayList;


public class CountryMealAdapter extends RecyclerView.Adapter<CountryMealAdapter.viewHolder>{

   Context context;
    private ArrayList<Countrys> country;
     OnClickCountry listener;

    public CountryMealAdapter(CountryMealsActivity context, ArrayList<Countrys> country , OnClickCountry listener  ) {
        this.context = context;
        this.country = country;
        this.listener =  listener;

    }
    @NonNull
    @Override
    public CountryMealAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater  = (LayoutInflater) context .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.search_by_country , parent , false);
       viewHolder vh = new viewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CountryMealAdapter.viewHolder holder, int position) {

        holder.title.setText(country.get(position).getName());
        holder.img.setImageResource(country.get(position).getImage());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(holder.title.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return country.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView img;

        ConstraintLayout layout;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_search);
            img = itemView.findViewById(R.id.img_search);
            layout=itemView.findViewById(R.id.layout_country);
        }
    }

    public void setList(ArrayList<Countrys>meals){
        this.country = meals;
    }
}

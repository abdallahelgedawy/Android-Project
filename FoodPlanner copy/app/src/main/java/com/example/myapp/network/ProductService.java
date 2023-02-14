package com.example.myapp.network;

import com.example.myapp.model.RandomMeals;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductService {
   @GET("search.php?f=a")
   public Observable<RandomMeals> getMeals();
   @GET("search.php?s=")
    public Observable<RandomMeals> getMeal();

   @GET("filter.php?")
   public Observable<RandomMeals> getCountryMeal(@Query("a") String name);
}

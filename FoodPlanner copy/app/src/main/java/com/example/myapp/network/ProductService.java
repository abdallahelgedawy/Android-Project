package com.example.myapp.network;



import com.example.myapp.model.Categories;
import com.example.myapp.model.RandomMeals;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductService {
   @GET("random.php")
   public Observable<RandomMeals> getMeals();
   @GET("search.php?")
    public Observable<RandomMeals> getAllMeals(@Query("s") String name);
   @GET("filter.php?")
   public Observable<RandomMeals> getCountryMeal(@Query("a") String name);
   @GET("categories.php")
   public Observable<Categories> getCategories();
   @GET ("filter.php?")
   public Observable<RandomMeals> getCategoryName(@Query("c") String categorySelected);
   @GET("list.php?i=list")
   public Observable<RandomMeals> getIngredientsByName();
   @GET("filter.php?")
   public Observable<RandomMeals> getIngredients(@Query("i") String IngredientSelected);
   @GET("search.php?")
     public Observable<RandomMeals> getMeal(@Query("s") String name);



}

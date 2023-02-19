package com.example.myapp.network;


public interface RemoteSource {

    public void getDailyMeal(NetworkDelegate networkDelegate);
    public void getAllMeal(SpecialDelegate networkDelegate , String name);
    public void getCountryMeal(NetworkDelegate networkDelegate,String name);
    public void getCategory(CategoryDelegate networkDelegate);
    public void getCategorybyname(NetworkDelegate networkDelegate , String name);

    public void getIngredients(NetworkDelegate networkDelegate);
    public void getIngredientsname(NetworkDelegate networkDelegate , String name);
    public void getMeal(NetworkDelegate networkDelegate , String name);

}
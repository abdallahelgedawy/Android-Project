package com.example.myapp.network;


public interface RemoteSource {

    public void getDailyMeal(NetworkDelegate networkDelegate);
    public void getAllMeal(SpecialDelegate networkDelegate);
    public void getCountryMeal(NetworkDelegate networkDelegate,String name);

}
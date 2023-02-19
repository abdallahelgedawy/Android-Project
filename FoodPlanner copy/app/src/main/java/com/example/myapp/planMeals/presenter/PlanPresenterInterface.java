package com.example.myapp.planMeals.presenter;

import com.example.myapp.model.Meals;

public interface PlanPresenterInterface {
    public void getFavMeals();
    public void delete(Meals meals);
    public void getSaturday();
    public void getSunday();
    public void getMonday();
    public void getTuesday();
    public void getWednesday();
    public void getThursday();
    public void getFriday();

}

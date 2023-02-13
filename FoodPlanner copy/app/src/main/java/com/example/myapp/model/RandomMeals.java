package com.example.myapp.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class RandomMeals {
    ArrayList<Meals> meals;
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    public ArrayList<Meals> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Meals> meals) {
        this.meals = meals;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}


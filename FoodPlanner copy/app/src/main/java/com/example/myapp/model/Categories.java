package com.example.myapp.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Categories {
    private ArrayList<Category> categories;
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}

package com.example.myapp.model;

import com.example.myapp.db.LocalSource;
import com.example.myapp.network.RemoteSource;

import java.util.List;

public interface RepositoryInterface extends RemoteSource, LocalSource {
    List<Meals> getFavoriteMeals();
}

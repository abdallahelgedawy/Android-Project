package com.example.myapp.model;

import com.example.myapp.db.LocalSource;
import com.example.myapp.network.RemoteSource;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public interface RepositoryInterface extends RemoteSource, LocalSource {
    List<Meals> getFavoriteMeals();
    Observable<List<Meals>> getMealsSaturday();
}

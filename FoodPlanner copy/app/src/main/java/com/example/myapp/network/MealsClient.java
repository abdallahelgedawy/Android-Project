package com.example.myapp.network;

import android.util.Log;

import com.example.myapp.model.Meals;
import com.example.myapp.model.RandomMeals;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealsClient implements RemoteSource{

        private static final String Tag = "MealsClient";
        private static final String Base_url = "https://themealdb.com/api/json/v1/1/";
        private static MealsClient client = null;
        Meals meal;

        private MealsClient() {
        }

        public static MealsClient getInstance() {
            if (client == null) {
                client = new MealsClient();
            }
            return client;
        }

        public ProductService enqueueCall() {
            Retrofit myRetrofit = new Retrofit.Builder().baseUrl(Base_url).
                    addConverterFactory(GsonConverterFactory.create()).
                    addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
            ProductService myApi = myRetrofit.create(ProductService.class);
            return myApi;
        }
        @Override
        public void getDailyMeal(NetworkDelegate networkDelegate){
         ProductService myApi = enqueueCall();
            Observable<RandomMeals> observable = myApi.getMeals().
                    subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
            observable.subscribe(o -> networkDelegate.onSuccessResult(o.getMeals()));
        }

    @Override
    public void getAllMeal(SpecialDelegate networkDelegate) {
        ProductService myApi = enqueueCall();
        Observable<RandomMeals> observable = myApi.getMeal().
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(item->networkDelegate.onpecialResult(item.getMeals()) );
    }

    @Override
    public void getCountryMeal(NetworkDelegate networkDelegate, String name) {
        ProductService myApi = enqueueCall();
        Observable<RandomMeals> observable = myApi.getCountryMeal(name).
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(item->networkDelegate.onSuccessResult(item.getMeals()) );
    }
}







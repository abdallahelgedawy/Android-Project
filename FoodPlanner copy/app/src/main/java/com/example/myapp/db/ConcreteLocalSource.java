package com.example.myapp.db;

import android.annotation.SuppressLint;
import android.content.Context;
import com.example.myapp.model.Meals;
import java.util.List;
import android.util.Log;
import android.widget.Toast;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;



public class ConcreteLocalSource implements LocalSource {
    private Context context;
    private MealsDao dao;
    private List<Meals> favoritemeal;
    private static ConcreteLocalSource instance = null;


    @SuppressLint("CheckResult")
    public ConcreteLocalSource(Context context) {
        this.context = context;
        AppDataBase appDataBase=AppDataBase.getInstance(context.getApplicationContext());
        dao=appDataBase.mealsDao();
        dao.getMeals().subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( item ->favoritemeal=item);
    }

    public static ConcreteLocalSource getInstance(Context context){
        if(instance ==null){
            instance = new ConcreteLocalSource(context);
        }
        return instance;
    }

    @Override
    public void delete(Meals meals) {
        dao.deleteProduct(meals).subscribeOn(Schedulers.computation()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                Toast.makeText(context.getApplicationContext(), "removed", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {
                Log.i("errr",e.getMessage());

            }
        });

    }

    @Override
    public void insert(Meals meals) {

        dao.insertProduct(meals).subscribeOn(Schedulers.computation()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                Toast.makeText(context.getApplicationContext(), "added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {


            }

            @Override
            public void onError(Throwable e) {
                Log.i("error",e.getMessage());

            }
        });
    }

    @Override
    public List<Meals> getFavoriteMeals() {
        return favoritemeal;
    }
}

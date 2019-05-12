package com.example.employee;

import android.app.Activity;
import android.app.Application;

import com.example.employee.data.DataManager;
import com.example.employee.data.local.model.Employee;
import com.example.employee.di.componets.DaggerAppComponent;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class App extends Application implements HasActivityInjector {

    @Inject
    DataManager dataManager;

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerAppComponent.builder().application(this).build().inject(this);

        dataManager.getEmployeesFromServer()
                .flatMap((Function<List<Employee>, SingleSource<?>>) employees -> dataManager.insertEmployees(employees))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }
}

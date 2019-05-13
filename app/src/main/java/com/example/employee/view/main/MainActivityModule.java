package com.example.employee.view.main;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.employee.data.DataManager;
import com.example.employee.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;


@Module
public class MainActivityModule {

    @Provides
    MainViewModel provideMainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new MainViewModel(dataManager, schedulerProvider);
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(MainActivity mainActivity) {
        return new LinearLayoutManager(mainActivity);
    }

    @Provides
    EmployeeAdapter provideEmployeeAdapter() {
        return new EmployeeAdapter();
    }
}

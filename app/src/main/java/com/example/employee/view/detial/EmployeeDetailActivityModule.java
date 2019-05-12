package com.example.employee.view.detial;

import com.example.employee.data.DataManager;
import com.example.employee.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class EmployeeDetailActivityModule {

    @Provides
    EmployeeDetailViewModel provideEmployeeDetailViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new EmployeeDetailViewModel(dataManager, schedulerProvider);
    }


}

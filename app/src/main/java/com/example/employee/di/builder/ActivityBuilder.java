package com.example.employee.di.builder;


import com.example.employee.view.detial.EmployeeDetailActivity;
import com.example.employee.view.detial.EmployeeDetailActivityModule;
import com.example.employee.view.main.MainActivity;
import com.example.employee.view.main.MainActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = EmployeeDetailActivityModule.class)
    abstract EmployeeDetailActivity bindEmployeeDetailActivity();

}

package com.example.employee.di.module;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;


import com.example.employee.data.AppDataManager;
import com.example.employee.data.DataManager;
import com.example.employee.data.local.db.AppDatabase;

import com.example.employee.data.local.db.AppDbHelper;
import com.example.employee.data.local.db.DbHelper;
import com.example.employee.data.remote.ApiHelper;
import com.example.employee.data.remote.AppApiHelper;
import com.example.employee.di.DatabaseInfo;
import com.example.employee.utils.AppConstants;
import com.example.employee.utils.rx.AppSchedulerProvider;
import com.example.employee.utils.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class AppModule {

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@DatabaseInfo String dbName, Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, dbName).fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }


    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }


    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }


}

package com.example.employee.data.local.db;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.employee.data.local.model.Employee;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class AppDbHelper implements DbHelper {

    private final AppDatabase appDatabase;

    @Inject
    public AppDbHelper(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }


    @Override
    public LiveData<List<Employee>> getEmployeesLiveData() {
        return appDatabase.employeeDao().getEmployeesLiveData();
    }

    @Override
    public LiveData<Employee> getEmployeeLiveData(String id) {
        return appDatabase.employeeDao().getEmployeeLiveData(id);
    }

    @Override
    public Single<List<Employee>> getEmployees() {
        return appDatabase.employeeDao().getEmployees();
    }

    @Override
    public Single<Employee> getEmployee(String id) {
        return appDatabase.employeeDao().getEmployee(id);
    }

    @Override
    public Single<Long> insertEmployee(final Employee employee) {
        return Single.fromCallable(() -> appDatabase.employeeDao().insertEmployee(employee));
    }

    @Override
    public Single<Boolean> insertEmployees(final List<Employee> employees) {
        return Single.fromCallable(() -> {
            appDatabase.employeeDao().insertEmployees(employees);
            return true;
        });
    }

    @Override
    public Single<Boolean> deleteEmployee(final Employee employee) {
        return Single.fromCallable(() -> {
            appDatabase.employeeDao().deleteEmployee(employee);
            return true;
        });
    }

    @Override
    public Single<Boolean> deleteEmployees(final List<Employee> employees) {
        return Single.fromCallable(() -> {
            appDatabase.employeeDao().deleteEmployees(employees);
            return true;
        });
    }

    @Override
    public Single<Boolean> deleteByEmployeeId(final String id) {
        return Single.fromCallable(() -> {
            appDatabase.employeeDao().deleteByEmployeeId(id);
            return true;
        });
    }


}

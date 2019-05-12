package com.example.employee.data;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.employee.data.local.db.DbHelper;
import com.example.employee.data.local.model.Employee;
import com.example.employee.data.remote.ApiHelper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class AppDataManager implements DataManager {

    private final Context context;
    private final DbHelper mDbHelper;
    private final ApiHelper mApiHelper;


    @Inject
    public AppDataManager(Context context, DbHelper mDbHelper, ApiHelper apiHelper) {
        this.context = context;
        this.mDbHelper = mDbHelper;
        this.mApiHelper = apiHelper;
    }

    @Override
    public LiveData<List<Employee>> getEmployeesLiveData() {
        return mDbHelper.getEmployeesLiveData();
    }

    @Override
    public LiveData<Employee> getEmployeeLiveData(String id) {
        return mDbHelper.getEmployeeLiveData(id);
    }

    @Override
    public Single<List<Employee>> getEmployees() {
        return mDbHelper.getEmployees();
    }

    @Override
    public Single<Employee> getEmployee(String id) {
        return mDbHelper.getEmployee(id);
    }

    @Override
    public Single<Long> insertEmployee(Employee employee) {
        return mDbHelper.insertEmployee(employee);
    }

    @Override
    public Single<Boolean> insertEmployees(List<Employee> employees) {
        return mDbHelper.insertEmployees(employees);
    }

    @Override
    public Single<Boolean> deleteEmployee(Employee employee) {
        return mDbHelper.deleteEmployee(employee);
    }

    @Override
    public Single<Boolean> deleteEmployees(List<Employee> employees) {
        return mDbHelper.deleteEmployees(employees);
    }

    @Override
    public Single<Boolean> deleteByEmployeeId(String id) {
        return mDbHelper.deleteByEmployeeId(id);
    }

    @Override
    public Single<List<Employee>> getEmployeesFromServer() {
        return mApiHelper.getEmployeesFromServer();
    }

    @Override
    public Single<Employee> getEmployeeFromServer(String id) {
        return mApiHelper.getEmployeeFromServer(id);
    }

    @Override
    public Single<Employee> updateEmployeeAtServer(Employee employee) {
        return mApiHelper.updateEmployeeAtServer(employee);
    }
}

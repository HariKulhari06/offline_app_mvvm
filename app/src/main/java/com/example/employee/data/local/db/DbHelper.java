package com.example.employee.data.local.db;

import androidx.lifecycle.LiveData;

import com.example.employee.data.local.model.Employee;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface DbHelper {

    LiveData<List<Employee>> getEmployeesLiveData();

    Observable<LiveData<List<Employee>>> searchEmployee(String text);

    LiveData<Employee> getEmployeeLiveData(String id);

    Single<List<Employee>> getEmployees();

    Single<Employee> getEmployee(String id);

    Single<Long> insertEmployee(Employee employee);

    Single<Boolean> insertEmployees(List<Employee> employees);

    Single<Boolean> deleteEmployee(Employee employee);

    Single<Boolean> deleteEmployees(List<Employee> employees);

    Single<Boolean> deleteByEmployeeId(String id);
}

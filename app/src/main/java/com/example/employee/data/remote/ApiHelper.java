package com.example.employee.data.remote;

import com.example.employee.data.local.model.Employee;

import java.util.List;

import io.reactivex.Single;

public interface ApiHelper {

    Single<List<Employee>> getEmployeesFromServer();

    Single<Employee> getEmployeeFromServer(String id);

    Single<Employee> updateEmployeeAtServer(Employee employee);

}

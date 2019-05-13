package com.example.employee.view.detial;

import com.example.employee.data.local.model.Employee;

public interface EmployeDetailNavigator {
    void onEmployeeDelete();
    void handleError(Throwable throwable);

    void onEmployeeUpdate(Employee employee);
}

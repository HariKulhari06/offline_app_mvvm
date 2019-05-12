package com.example.employee.view.detial;

import androidx.lifecycle.MutableLiveData;

import com.example.employee.data.DataManager;
import com.example.employee.data.local.model.Employee;
import com.example.employee.utils.rx.SchedulerProvider;
import com.example.employee.view.base.BaseViewModel;

public class EmployeeDetailViewModel extends BaseViewModel<EmployeDetailNavigator> {

    MutableLiveData<Employee> data = new MutableLiveData<>();

    public EmployeeDetailViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }


    public Employee getData() {
        return data.getValue();
    }

    public void loadEmployeeDetails(String id) {
        data.setValue(getDataManager().getEmployeeLiveData(id).getValue());
    }
}

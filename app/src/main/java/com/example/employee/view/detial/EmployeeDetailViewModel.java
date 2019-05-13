package com.example.employee.view.detial;

import com.example.employee.data.DataManager;
import com.example.employee.data.local.model.Employee;
import com.example.employee.utils.rx.SchedulerProvider;
import com.example.employee.view.base.BaseViewModel;

import org.json.JSONObject;

import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

public class EmployeeDetailViewModel extends BaseViewModel<EmployeDetailNavigator> {

    private Employee mEmployee;

    public EmployeeDetailViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public Employee getEmployee() {
        return mEmployee;
    }

    public void setEmployee(Employee employee) {
        mEmployee = employee;
    }

    void deleteEmployee() {
        getCompositeDisposable().add(getDataManager().deleteEmployeeFromServer(getEmployee().id)
                .flatMap(new Function<JSONObject, SingleSource<?>>() {
                    @Override
                    public SingleSource<?> apply(JSONObject jsonObject) throws Exception {
                        return getDataManager().deleteByEmployeeId(getEmployee().id);
                    }
                }).subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(o -> getNavigator().onEmployeeDelete(), throwable -> getNavigator().handleError(throwable)));
    }


    void UpdateEmployee() {
        getEmployee().employeeName = "hari Singh Kulhari";
        getCompositeDisposable().add(getDataManager().updateEmployeeAtServer(getEmployee())
                .flatMap(new Function<Employee, SingleSource<?>>() {
                    @Override
                    public SingleSource<?> apply(Employee employee) throws Exception {
                        getEmployee().employeeName = employee.employeeName;
                        getEmployee().employeeAge = employee.employeeAge;
                        getEmployee().employeeSalary = employee.employeeSalary;
                        return getDataManager().insertEmployee(getEmployee());
                    }
                })
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(o -> getNavigator().onEmployeeUpdate(getEmployee()), throwable -> getNavigator().handleError(throwable)));
    }


}

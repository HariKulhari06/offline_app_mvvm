package com.example.employee.view.detial;

import androidx.databinding.ObservableBoolean;

import com.example.employee.data.DataManager;
import com.example.employee.data.local.model.Employee;
import com.example.employee.utils.rx.SchedulerProvider;
import com.example.employee.view.base.BaseViewModel;

import org.json.JSONObject;

import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

public class EmployeeDetailViewModel extends BaseViewModel<EmployeDetailNavigator> {

    private Employee mEmployee;
    private ObservableBoolean edit = new ObservableBoolean();
    private ObservableBoolean showProgress = new ObservableBoolean();


    public EmployeeDetailViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        edit.set(false);
        setShowProgress(false);
    }

    public ObservableBoolean getShowProgress() {
        return showProgress;
    }

    public void setShowProgress(boolean showProgress) {
        this.showProgress.set(showProgress);
    }

    public Employee getEmployee() {
        return mEmployee;
    }

    public void setEmployee(Employee employee) {
        mEmployee = employee;
    }


    public ObservableBoolean isEdit() {
        return edit;
    }

    public void setEdit() {
        if (isEdit().get()) {
            getNavigator().updateEmployee();
        } else {
            edit.set(true);
        }
    }

    public void deleteEmployee() {
        setShowProgress(true);
        getCompositeDisposable().add(getDataManager().deleteEmployeeFromServer(getEmployee().id)
                .flatMap((Function<JSONObject, SingleSource<?>>) jsonObject -> getDataManager().deleteByEmployeeId(getEmployee().id)).subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(o -> {
                    setShowProgress(false);
                    getNavigator().onEmployeeDelete();

                }, throwable -> {
                    setShowProgress(false);
                    getNavigator().handleError(throwable);
                }));
    }


    void doUpdateEmployee(Employee emp) {
        setShowProgress(true);
        getCompositeDisposable().add(getDataManager().updateEmployeeAtServer(emp)
                .flatMap((Function<Employee, SingleSource<?>>) employee -> {
                    setEmployee(emp);
                    getEmployee().employeeName = employee.employeeName;
                    getEmployee().employeeAge = employee.employeeAge;
                    getEmployee().employeeSalary = employee.employeeSalary;
                    return getDataManager().insertEmployee(emp);
                })
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(o -> {
                            setShowProgress(false);
                            edit.set(false);
                        }, throwable -> {
                            setShowProgress(false);
                            getNavigator().handleError(throwable);
                        }
                ));
    }


}

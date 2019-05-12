package com.example.employee.view.main;

import androidx.lifecycle.LiveData;

import com.example.employee.data.DataManager;
import com.example.employee.data.local.model.Employee;
import com.example.employee.utils.rx.SchedulerProvider;
import com.example.employee.view.base.BaseViewModel;

import java.util.List;

import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

public class MainViewModel extends BaseViewModel<MainNavigator> {

    private LiveData<List<Employee>> listLiveData;


    public MainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        listLiveData = getDataManager().getEmployeesLiveData();
    }

    LiveData<List<Employee>> getListLiveData() {
        return listLiveData;
    }


    void refresh() {
        getCompositeDisposable().add(getDataManager().getEmployeesFromServer()
                .flatMap((Function<List<Employee>, SingleSource<?>>) employees -> getDataManager().insertEmployees(employees)).subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(o -> setIsLoading(false), throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

}


package com.example.employee.view.main;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.example.employee.data.DataManager;
import com.example.employee.data.local.model.Employee;
import com.example.employee.utils.rx.RxSearchObservable;
import com.example.employee.utils.rx.SchedulerProvider;
import com.example.employee.view.base.BaseViewModel;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.ObservableSource;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

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

    @SuppressLint("CheckResult")
    public void setUpSearchObservable(MainActivity mainActivity, LifecycleOwner owner, SearchView searchView) {

        RxSearchObservable.fromView(searchView)
                .debounce(300, TimeUnit.MILLISECONDS)
                .filter(text -> {
                    if (text.isEmpty()) {
                        mainActivity.runOnUiThread(() -> setOriginalList(owner));
                        return false;
                    } else {
                        return true;
                    }
                })
                .distinctUntilChanged()
                .switchMap((Function<String, ObservableSource<LiveData<List<Employee>>>>) query -> getDataManager().searchEmployee(query))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(users -> {
                    listLiveData.removeObservers(owner);
                    listLiveData = users;
                    getNavigator().startListening();
                }, throwable -> {
                    getNavigator().handleError(throwable);
                });


        searchView.setOnCloseListener(() -> {
            if (!TextUtils.isEmpty(RxSearchObservable.getFilterText()))
                setOriginalList(owner);

            return true;
        });

    }

    private void setOriginalList(LifecycleOwner owner) {
        listLiveData.removeObservers(owner);
        listLiveData = getDataManager().getEmployeesLiveData();
        getNavigator().startListening();
    }
}


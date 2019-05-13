package com.example.employee.data.remote;

import com.example.employee.data.local.model.Employee;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONObject;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class AppApiHelper implements ApiHelper {

    @Inject
    public AppApiHelper() {
    }

    @Override
    public Single<List<Employee>> getEmployeesFromServer() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_GET_EMPLOYEES)
                .build()
                .getObjectListSingle(Employee.class);
    }

    @Override
    public Single<Employee> getEmployeeFromServer(String id) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_GET_EMPLOYEE + "/" + id)
                .build()
                .getObjectSingle(Employee.class);
    }

    @Override
    public Single<Employee> updateEmployeeAtServer(Employee employee) {
        return Rx2AndroidNetworking.put(ApiEndPoint.ENDPOINT_UPDATE_EMPLOYEES+ "/" + employee.id)
                .addBodyParameter(employee)
                .build()
                .getObjectSingle(Employee.class);
    }

    @Override
    public Single<JSONObject> deleteEmployeeFromServer(String id) {
        return Rx2AndroidNetworking.delete(ApiEndPoint.ENDPOINT_DELETE_EMPLOYEE + "/" + id)
                .addQueryParameter(id)
                .build()
                .getJSONObjectSingle();
    }


}

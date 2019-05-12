package com.example.employee.data.remote;

import com.example.employee.BuildConfig;

public final class ApiEndPoint {
    public static final String ENDPOINT_GET_EMPLOYEES = BuildConfig.BASE_URL + "/employees";

    public static final String ENDPOINT_GET_EMPLOYEE = BuildConfig.BASE_URL + "/employee";

    public static final String ENDPOINT_DELETE_EMPLOYEE = BuildConfig.BASE_URL + "/delete";

    public static final String ENDPOINT_UPDATE_EMPLOYEES = BuildConfig.BASE_URL + "/update";
    public ApiEndPoint() {
    }
}

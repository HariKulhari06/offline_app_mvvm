package com.example.employee.data.local.model;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "employee")
public class Employee implements Serializable {

    public static final DiffUtil.ItemCallback<Employee> DIFF_CALLBACK = new DiffUtil.ItemCallback<Employee>() {
        @Override
        public boolean areItemsTheSame(@NonNull Employee oldItem, @NonNull Employee newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Employee oldItem, @NonNull Employee newItem) {
            return oldItem.employeeName.equals(newItem.employeeName);
        }
    };


    @Expose
    @SerializedName("id")
    @PrimaryKey
    @NonNull
    public String id;

    @Expose
    @SerializedName("employee_name")
    @ColumnInfo(name = "emp_name")
    public String employeeName;

    @Expose
    @SerializedName("employee_salary")
    @ColumnInfo(name = "emp_salary")
    public String employeeSalary;

    @Expose
    @SerializedName("employee_age")
    @ColumnInfo(name = "emp_age")
    public String employeeAge;

    @Expose
    @SerializedName("profile_image")
    @ColumnInfo(name = "profile_image")
    public String profileImage;


    public Employee() {
    }


    public String getEmployeeSalary() {
        return employeeSalary + " $";
    }

    public String getEmployeeAge() {
        return employeeAge + " Years";
    }
}

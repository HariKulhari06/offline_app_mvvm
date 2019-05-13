package com.example.employee.data.local.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.employee.data.local.model.Employee;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface EmployeeDao {

    @Query("SELECT * FROM employee ORDER BY id ASC")
    Single<List<Employee>> getEmployees();

    @Query("SELECT * FROM employee ORDER BY id ASC")
    LiveData<List<Employee>> getEmployeesLiveData();


    @Query("SELECT * FROM employee WHERE emp_name LIKE  '%' || :searchText  || '%' " +
            "OR id LIKE  '%' || :searchText  || '%' ORDER BY id ASC")
    LiveData<List<Employee>> getEmployeesLiveData(String searchText);

    @Query("SELECT * FROM employee WHERE id LIKE  '%' || :id LIMIT 1")
    Single<Employee> getEmployee(String id);

    @Query("SELECT * FROM employee WHERE id = :id")
    LiveData<Employee> getEmployeeLiveData(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertEmployee(Employee employee);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertEmployees(List<Employee> employees);

    @Delete
    void deleteEmployee(Employee employee);

    @Delete
    void deleteEmployees(List<Employee> employees);

    @Query("DELETE FROM employee WHERE id = :id")
    void deleteByEmployeeId(String id);
}

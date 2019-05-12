/*
 *
 *  Copyright (C) 2019 CORE TECHIES INDIA PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  https://coretechies.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 *
 */

package com.example.employee.data.local.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.employee.data.local.db.dao.EmployeeDao;
import com.example.employee.data.local.model.Employee;



@Database(entities = {Employee.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract EmployeeDao employeeDao();

}

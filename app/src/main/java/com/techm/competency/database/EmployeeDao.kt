package com.techm.competency.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.techm.competency.model.EmployeesResponseModel

/**
 * Interface for Employee Queries
 */
@Dao
interface EmployeeDao {

    @Query("SELECT * FROM employee_data_table")
    fun getAllEmployee(): LiveData<List<Employee>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployee(employee: Employee): Long

    @Insert
    suspend fun insertAll(employeeList: ArrayList<Employee>?)

    @Delete
    suspend fun delete(employee: Employee): Int

    @Query("DELETE FROM employee_data_table")
    suspend fun deleteAllEmployee()

    @Update
    suspend fun updateEmployee(todo: Employee): Int

    @Query("SELECT * FROM employee_data_table WHERE id = :id")
    suspend fun findByEmployeeId(id: String?): Employee
}
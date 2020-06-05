package com.techm.competency.database

import android.app.Application
import androidx.lifecycle.LiveData

/**
 * Repository module for handling data operations.
 */
class EmployeeRepository(application: Application) {

    private var employeeDao: EmployeeDao
    private var projectDao: ProjectDao
    private var allProjects: LiveData<List<Project>>
    private var allEmployeesResponseModel: LiveData<List<Employee>>

    /** Initialize Database,Employee DAO */
    init {
        val competencyDatabase = CompetencyDatabase.invoke(application)
        employeeDao = competencyDatabase.getEmployeeDao()
        projectDao = competencyDatabase.getProjectDao()
        allProjects = projectDao.getAllProject()
        allEmployeesResponseModel = employeeDao.getAllEmployee()
    }

    /** Function to fetch all project Details from Database */
    fun getAllProjectData(): LiveData<List<Project>> {
        return allProjects
    }

    /** Function to Insert Employee Details in Database */
    suspend fun insertEmployee(employees: Employee): Long {
        return employeeDao.insertEmployee(employees)
    }

    /** Function to Delete Employee Details from Database */
    suspend fun deleteEmployee(employees: Employee): Int {
        return employeeDao.delete(employees)
    }

    /** Function to Update Employee Details in Database */
    suspend fun updateEmployee(employees: Employee): Int {
        return employeeDao.updateEmployee(employees)
    }

    /** Function to fetch all Employee Details */
    fun getAllEmployeeData(): LiveData<List<Employee>> {
        return allEmployeesResponseModel
    }

    /** Function to fetch Specific Employee Details */
    suspend fun getEmployee(id: String): Employee {
        return employeeDao.findByEmployeeId(id)
    }

}

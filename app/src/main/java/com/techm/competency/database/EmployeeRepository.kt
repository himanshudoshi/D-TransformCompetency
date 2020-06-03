package com.techm.competency.database

import android.app.Application
import androidx.lifecycle.LiveData
import com.techm.competency.model.EmployeesResponseModel
import com.techm.competency.model.ProjectModel

class EmployeeRepository(application: Application) {

    var employeeDao: EmployeeDao
    var projectDao: ProjectDao
    var allProjects: LiveData<List<Project>>
    var allEmployeesResponseModel: LiveData<List<Employee>>

    init {
        val competencyDatabase= CompetencyDatabase.invoke(application)
        employeeDao=competencyDatabase.getEmployeeDao()
        projectDao=competencyDatabase.getProjectDao()
        allProjects=projectDao.getAllProject()
        allEmployeesResponseModel=employeeDao.getAllEmployee()
    }

    fun getAllProjectData():LiveData<List<Project>>{
        return allProjects
    }

    suspend fun insertEmployee(employees: Employee):Long
    {
        return employeeDao.insertEmployee(employees)
    }
    suspend fun deleteEmployee(employees: Employee):Int
    {
        return employeeDao.delete(employees)
    }
    suspend fun updateEmployee(employees: Employee):Int
    {
        return employeeDao.updateEmployee(employees)
    }
    fun getAllEmployeeData():LiveData<List<Employee>>{
        return allEmployeesResponseModel
    }
    suspend fun getEmployee(id: String):Employee
    {
        return employeeDao.findByEmployeeId(id)
    }

}

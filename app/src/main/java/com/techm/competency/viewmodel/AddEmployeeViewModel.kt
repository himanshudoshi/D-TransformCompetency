package com.techm.competency.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.techm.competency.database.Employee
import com.techm.competency.database.EmployeeRepository
import com.techm.competency.database.Project
import com.techm.competency.model.EmployeesResponseModel
import com.techm.competency.utils.ResponseStatus
import kotlinx.coroutines.launch
import org.jetbrains.annotations.NotNull

class AddEmployeeViewModel(@NotNull application: Application) :
    AndroidViewModel(application) {
    private var employeeRepository: EmployeeRepository = EmployeeRepository(application)
    var mEmployeeResponse: MutableLiveData<EmployeesResponseModel> =
        MutableLiveData<EmployeesResponseModel>()
    var mProjectData: LiveData<List<Project>> =
        employeeRepository.getAllProjectData()

    var mEmployeeResponseUpdate: MutableLiveData<Employee> =
        MutableLiveData<Employee>()

    /**
     * Calling room methods
     */
    fun insertEmployee(employeeInformation: Employee) = viewModelScope.launch {
        if (employeeRepository.insertEmployee(employeeInformation) > 0)
            mEmployeeResponse.value = EmployeesResponseModel("", ResponseStatus.SUCCESS)
        else
            mEmployeeResponse.value = EmployeesResponseModel("", ResponseStatus.FAIL)
    }
    fun updateEmployee(employeeInformation: Employee) = viewModelScope.launch {
        if (employeeRepository.updateEmployee(employeeInformation) > 0)
            mEmployeeResponse.value = EmployeesResponseModel("", ResponseStatus.SUCCESS)
        else
            mEmployeeResponse.value = EmployeesResponseModel("", ResponseStatus.FAIL)
    }

    fun getEmployeeData(employeeId: String)= viewModelScope.launch {
        mEmployeeResponseUpdate.value=employeeRepository.getEmployee(employeeId)
    }
}
package com.techm.competency.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.techm.competency.database.Employee
import com.techm.competency.database.EmployeeRepository
import com.techm.competency.model.EmployeeDeleteModel
import kotlinx.coroutines.launch
import org.jetbrains.annotations.NotNull

/**
 *  Employee View Model class to Display Employee Details
 */
class EmployeeViewModel(@NotNull application: Application) :
    AndroidViewModel(application) {

    private var employeeRepository: EmployeeRepository = EmployeeRepository(application)
    var mEmployeeInformationData: LiveData<List<Employee>> =
        employeeRepository.getAllEmployeeData()
    var mEmployeeDeleteStatusDeleteModel: MutableLiveData<EmployeeDeleteModel> =
        MutableLiveData<EmployeeDeleteModel>()

    fun getEmployeeInformation() {
        employeeRepository.getAllEmployeeData()

    }

    /** Delete employee Functionality  */
    fun deleteEmployee(employee: Employee) =
        viewModelScope.launch {
            employeeRepository.deleteEmployee(employee)
        }
}
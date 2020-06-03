package com.techm.competency.view

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.techm.competency.database.Employee
import com.techm.competency.database.EmployeeRepository
import com.techm.competency.model.EmployeesResponseModel
import com.techm.competency.database.Project
import com.techm.competency.model.ProjectModel
import com.techm.competency.viewmodel.AddEmployeeViewModel
import com.techm.competency.viewmodel.EmployeeViewModel
import io.reactivex.Maybe
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 *  Add Employee Activity Test
 */
class AddEmployeeActivityTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var mAddEmployeeViewModel: AddEmployeeViewModel
    lateinit var employeeRepository: EmployeeRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        val application = Mockito.mock(Application::class.java)
        this.employeeRepository = EmployeeRepository(application)
        this.mAddEmployeeViewModel = AddEmployeeViewModel(application)
    }

    /** This test should be Success because we will get success response  */
    @Test
    suspend fun test_getSingleEmployeeQuerySuccess() {
        Mockito.`when`(this.employeeRepository.getEmployee("1")).thenAnswer {
            return@thenAnswer Maybe.just(ArgumentMatchers.any<EmployeeViewModel>())
        }

        val observer = Mockito.mock(Observer::class.java) as Observer<EmployeesResponseModel>
        this.mAddEmployeeViewModel.mEmployeeResponse.observeForever(observer)

        this.mAddEmployeeViewModel.getEmployeeData("1")
        Thread.sleep(10000)
        assertNotNull(this.mAddEmployeeViewModel.mEmployeeResponse.value)
    }

    /** This test should be fail because we will get success response */
    @Test
    suspend fun test_getSingleEmployeeQueryError() {
        Mockito.`when`(this.employeeRepository.getEmployee("1")).thenAnswer {
            return@thenAnswer Maybe.just(ArgumentMatchers.any<EmployeeViewModel>())
        }

        val observer = Mockito.mock(Observer::class.java) as Observer<EmployeesResponseModel>
        this.mAddEmployeeViewModel.mEmployeeResponse.observeForever(observer)

        this.mAddEmployeeViewModel.getEmployeeData("1")
        Thread.sleep(10000)

        assertNull(this.mAddEmployeeViewModel.mEmployeeResponse.value)
    }

    /** This test should be success because we will get success response  */
    @Test
    suspend fun test_getProjectQuerySuccess() {
        Mockito.`when`(this.employeeRepository.getAllProjectData()).thenAnswer {
            return@thenAnswer Maybe.just(ArgumentMatchers.any<EmployeeViewModel>())
        }

        val observer = Mockito.mock(Observer::class.java) as Observer<List<Project>>
        this.mAddEmployeeViewModel.mProjectData.observeForever(observer)

        this.mAddEmployeeViewModel.mProjectData
        Thread.sleep(10000)
        assertNotNull(this.mAddEmployeeViewModel.mProjectData.value)
    }

    /** This test should be fail because we will get success response  */
    @Test
    suspend fun test_getProjectQueryError() {
        Mockito.`when`(this.employeeRepository.getAllProjectData()).thenAnswer {
            return@thenAnswer Maybe.just(ArgumentMatchers.any<EmployeeViewModel>())
        }

        val observer = Mockito.mock(Observer::class.java) as Observer<List<Project>>
        this.mAddEmployeeViewModel.mProjectData.observeForever(observer)

        this.mAddEmployeeViewModel.mProjectData
        Thread.sleep(10000)
        assertNull(this.mAddEmployeeViewModel.mProjectData.value)
    }


}
package com.techm.competency.view

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.techm.competency.database.Employee
import com.techm.competency.database.EmployeeRepository
import com.techm.competency.viewmodel.EmployeeViewModel
import io.reactivex.Maybe
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.net.SocketException

class EmployeeActivityTest{
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var mEmployeeViewModel: EmployeeViewModel
    lateinit var employeeRepository: EmployeeRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        val application = Mockito.mock(Application::class.java)
        this.employeeRepository = EmployeeRepository(application)
        this.mEmployeeViewModel = EmployeeViewModel(application)
    }
    @Test
    fun test_getEmployeeInformationQuerySuccess() {

        Mockito.`when`(this.employeeRepository.getAllEmployeeData()).thenAnswer {
            return@thenAnswer Maybe.just(ArgumentMatchers.any<EmployeeViewModel>())
        }

        val observer = Mockito.mock(Observer::class.java) as Observer<List<Employee>>
        this.mEmployeeViewModel.mEmployeeInformationData.observeForever(observer)

        this.mEmployeeViewModel.getEmployeeInformation()
        Thread.sleep(10000)
        assertNotNull(this.mEmployeeViewModel.mEmployeeInformationData.value)
    }
    /**
     * This test should be fail because we will get success response from API
     * */
    @Test
    fun test_getEmployeeInformationQueryError() {
        Mockito.`when`(this.employeeRepository.getAllEmployeeData()).thenAnswer {
            return@thenAnswer Maybe.error<SocketException>(SocketException("No network here"))
        }

        val observer = Mockito.mock(Observer::class.java) as Observer<List<Employee>>
        this.mEmployeeViewModel.mEmployeeInformationData.observeForever(observer)

        this.mEmployeeViewModel.getEmployeeInformation()
        Thread.sleep(10000)
        assertNull(this.mEmployeeViewModel.mEmployeeInformationData.value)
    }
}
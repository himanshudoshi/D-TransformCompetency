package org.dtransform.competencyapp.ui.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.techm.competency.CompetencyDatabaseTest
import com.techm.competency.database.Employee
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
open class EmployeeDaoTest : CompetencyDatabaseTest() {

    @Test
    fun insertEmployeeEntity() = runBlocking {
        val employeeEntity = Employee(
            id=1,
            employeeId = "483723",
            band = "U4",
            designation = "Technical Lead",
            name = "Himanshu Doshi",
            competency = "Android",
            project = "Bell Canada"
        )

        val employeeId = appDatabase.getEmployeeDao().insertEmployee(employeeEntity)
        Assert.assertEquals(employeeId, 1)
    }

    @Test
    fun getEmployeeIsNullOrEmpty(): Unit = runBlocking {
        withContext(Dispatchers.Main) {
            val employeeEntity = Employee(
                id = 1,
                employeeId = "483723",
                band = "U4",
                designation = "Technical Lead",
                name = "Himanshu Doshi",
                competency = "Android",
                project = "Bell Canada"
            )

            val employeeId = appDatabase.getEmployeeDao().insertEmployee(employeeEntity)

            val employeeList = appDatabase.getEmployeeDao().getAllEmployee()
            Assert.assertEquals(employeeList.value?.size, null)
        }
    }
}
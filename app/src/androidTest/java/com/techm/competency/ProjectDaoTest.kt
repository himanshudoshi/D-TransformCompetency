package org.dtransform.competencyapp.ui.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.techm.competency.CompetencyDatabaseTest
import com.techm.competency.database.Project
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
open class ProjectDaoTest : CompetencyDatabaseTest() {

    @Test
    fun insertProjectEntity() = runBlocking {
        val projectEntity = Project(id=1,projectName = "DTransform")
        val projectId = appDatabase.getProjectDao().insertProject(projectEntity)
        assertEquals(projectId, 1)
    }

    @Test
    fun getProjectsIsNullOrEmpty(): Unit = runBlocking {
        withContext(Main) {
            val projectEntity = Project(id=1,projectName = "DTransform")
            val projectId = appDatabase.getProjectDao().insertProject(projectEntity)
            val projectList = appDatabase.getProjectDao().getAllProject()
            assertEquals(projectList.value?.size,null)
        }
    }

}
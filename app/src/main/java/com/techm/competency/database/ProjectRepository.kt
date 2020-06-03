package com.techm.competency.database

import android.app.Application
import androidx.lifecycle.LiveData
import com.techm.competency.model.EmployeesResponseModel
import com.techm.competency.model.ProjectModel

class ProjectRepository {

    lateinit var projectDao: ProjectDao
    lateinit var allProjects: LiveData<List<Project>>
    constructor(application: Application)
    {
        val competencyDatabase= CompetencyDatabase.invoke(application)
        projectDao=competencyDatabase.getProjectDao()
        allProjects=projectDao.getAllProject()
    }
    fun getAllProjectData():LiveData<List<Project>>{
        return allProjects
    }
    suspend fun insertProject(mProject:Project):Long
    {
        return projectDao.insertProject(mProject)
    }

}
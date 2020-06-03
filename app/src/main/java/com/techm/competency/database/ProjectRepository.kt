package com.techm.competency.database

import android.app.Application
import androidx.lifecycle.LiveData

/**
 * Repository module for handling data operations.
 */
class ProjectRepository {

    private lateinit var projectDao: ProjectDao
    private lateinit var allProjects: LiveData<List<Project>>

    constructor(application: Application) {
        val competencyDatabase = CompetencyDatabase.invoke(application)
        projectDao = competencyDatabase.getProjectDao()
        allProjects = projectDao.getAllProject()
    }

    fun getAllProjectData(): LiveData<List<Project>> {
        return allProjects
    }

    suspend fun insertProject(mProject: Project): Long {
        return projectDao.insertProject(mProject)
    }

}

package com.techm.competency.database

import android.app.Application
import androidx.lifecycle.LiveData

/**
 * Repository module for handling data operations.
 */
class ProjectRepository {

    private lateinit var projectDao: ProjectDao
    private lateinit var allProjects: LiveData<List<Project>>

    /** Initialize Database,Project DAO */
    constructor(application: Application) {
        val competencyDatabase = CompetencyDatabase.invoke(application)
        projectDao = competencyDatabase.getProjectDao()
        allProjects = projectDao.getAllProject()
    }

    /** Function to fetch all project Details from Database */
    fun getAllProjectData(): LiveData<List<Project>> {
        return allProjects
    }

    /** Function to Insert Project Detail in Database */
    suspend fun insertProject(mProject: Project): Long {
        return projectDao.insertProject(mProject)
    }

    /** Function to Delete Project Details from Database */
    suspend fun deleteProject(mProject:Project): Int {
        return projectDao.delete(mProject)
    }

}

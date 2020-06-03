package com.techm.competency.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.techm.competency.database.Project
import com.techm.competency.database.ProjectRepository
import com.techm.competency.model.ProjectModel
import com.techm.competency.utils.ResponseStatus
import kotlinx.coroutines.launch
import org.jetbrains.annotations.NotNull

class ProjectViewModel(@NotNull application: Application) :
    AndroidViewModel(application) {

    lateinit var mProject: Project
    private var projectRepository: ProjectRepository = ProjectRepository(application)
    var mProjectData: LiveData<List<Project>> =
        projectRepository.getAllProjectData()

    var mProjectStatus: MutableLiveData<ProjectModel> =
        MutableLiveData<ProjectModel>()

    fun insertProject(projectName: String) = viewModelScope.launch {
        mProject = Project(0, projectName)
        if (projectRepository.insertProject(mProject) > 0)
            mProjectStatus.value = ProjectModel("", ResponseStatus.SUCCESS)
        else
            mProjectStatus.value = ProjectModel("", ResponseStatus.FAIL)
    }
}
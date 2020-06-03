package com.techm.competency.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.techm.competency.R
import com.techm.competency.adapter.ProjectRecyclerViewAdapter
import com.techm.competency.application.ApplicationContext
import com.techm.competency.database.Project
import com.techm.competency.utils.ResponseStatus
import com.techm.competency.utils.toast
import com.techm.competency.viewmodel.ProjectViewModel
import kotlinx.android.synthetic.main.activity_project.*

/**
 * Activity that displays all Project details in the app
 */
class ProjectActivity : AppCompatActivity(), ProjectRecyclerViewAdapter.ItemClickListener {

    private lateinit var projectViewModel: ProjectViewModel
    private lateinit var mProjectAdapter: ProjectRecyclerViewAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project)
        supportActionBar?.title = getString(R.string.add_project)
        projectViewModel = ViewModelProvider(this).get(ProjectViewModel::class.java)

        /** Adapter for initialize */
        mProjectAdapter = ProjectRecyclerViewAdapter(ArrayList(), ApplicationContext.context, this)
        linearLayoutManager = LinearLayoutManager(this)
        projectList.layoutManager = linearLayoutManager
        projectList.adapter = mProjectAdapter

        buttonAdd.setOnClickListener {
            if (projectName.text.toString().isNotEmpty()) {
                projectViewModel.insertProject(projectName.text.toString())
                projectName.setText("")
            }
        }
        /** Live data observer */
        projectViewModel.mProjectStatus.observe(this, Observer {

            when (it.status) {
                ResponseStatus.SUCCESS -> {
                    toast(getString(R.string.record_save_successfully))
                }
                ResponseStatus.FAIL ->
                    toast(getString(R.string.serviceFailureError))
            }
        })
        projectViewModel.mProjectData.observe(this, Observer {
            mProjectAdapter.setList(it)
        })
    }

    override fun onItemClick(mProject: Project, position: Int) {

    }
}

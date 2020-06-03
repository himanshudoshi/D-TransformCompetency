package com.techm.competency.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.techm.competency.R
import com.techm.competency.database.Project
import kotlinx.android.synthetic.main.item_layout.view.project
import kotlin.collections.ArrayList

class ProjectRecyclerViewAdapter: RecyclerView.Adapter<ProjectViewHolder> {
    private var itemsList = ArrayList<Project>()
    private var items = ArrayList<Project>()
    lateinit var context: Context
    private lateinit var listener: ItemClickListener

    constructor(
        items: ArrayList<Project>,
        context: Context?,
        listener: ItemClickListener
    ) {
        this.items = items
        this.listener = listener;
        if (context != null) {
            this.context = context
        }
        itemsList.addAll(items)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun removeAt(position: Int) {
        this.items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun getItemAtPosition(position: Int): Project {
        return this.items[position]
    }

    /**
     * setting updated list
     * */
    fun setList(dataInformation: List<Project>) {
        this.items = dataInformation as ArrayList<Project>
        itemsList.addAll(items)
        notifyDataSetChanged()
    }

    interface ItemClickListener {
        fun onItemClick(mProject: Project, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        return ProjectViewHolder(LayoutInflater.from(context).inflate(R.layout.item_project, parent, false))
    }

    override fun onBindViewHolder(holderViewHolder: ProjectViewHolder, position: Int) {
        holderViewHolder.projectName.text = items[position].projectName

        holderViewHolder.bind(items[position], position, listener)

    }
}

class ProjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(
        project: Project,
        position: Int,
        listener: ProjectRecyclerViewAdapter.ItemClickListener
    ) {
        itemView.setOnClickListener {
            listener.onItemClick(project, position)
        }
    }

    val projectName: TextView = view.project
}
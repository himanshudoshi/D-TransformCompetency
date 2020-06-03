package com.techm.competency.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.techm.competency.R
import com.techm.competency.database.Project
import kotlinx.android.synthetic.main.item_project.view.*

/** Class Adapter to populate items in Spinner */
class SpinnerAdapter(val context: Context, private var projectList: ArrayList<Project>) :
    BaseAdapter() {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val vh: ItemRowHolder
        val view: View = mInflater.inflate(R.layout.item_project, parent, false)
        vh = ItemRowHolder(view)
        view?.tag = vh

        vh.label.text = projectList[position].projectName
        return view
    }

    /** set items in list and update Spinner */
    fun setLIst(projectList: ArrayList<Project>) {
        this.projectList = projectList
        notifyDataSetChanged()
    }

    /** Return item of Projects */
    override fun getItem(position: Int): Project {
        return projectList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    /** Return item counts of Projects */
    override fun getCount(): Int {
        return projectList.size
    }

    fun getPosition(projectName: String): Int {

        run {
            var index = 0
            while (index < projectList.size) {
                if (getItem(index).projectName == projectName) {
                    return index
                }
                ++index
            }
        }
        return 0
    }

    private class ItemRowHolder(row: View) {
        val label: TextView = row.project
    }
}
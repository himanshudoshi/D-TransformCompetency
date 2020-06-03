package com.techm.competency.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.techm.competency.R
import com.techm.competency.database.Employee
import kotlinx.android.synthetic.main.item_layout.view.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * This class for creating items in recycler view
 * */
class EmployeesRecyclerViewAdapter : RecyclerView.Adapter<ViewHolder> {
    private var itemsList = ArrayList<Employee>()
    private var items = ArrayList<Employee>()
    lateinit var context: Context
    private lateinit var listener: ItemClickListener

    constructor(
        items: ArrayList<Employee>,
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

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false))
    }

    // Binds each object in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.employeeName.text = items[position].name
        holder.designation.text = items[position].designation
        holder.project.text = items[position].project
        holder.band.text = "("+items[position].band+")"
        holder.employeeId.text = items[position].employeeId
        holder.competency.text = items[position].competency

        holder.bind(items[position], position, listener)

        when (items[position].competency) {
            context.resources.getString(R.string.android) ->
                holder.employeeImage.load(context.resources.getDrawable(R.drawable.android))

            context.resources.getString(R.string.ios) ->
                holder.employeeImage.load(context.resources.getDrawable(R.drawable.ios))

            context.resources.getString(R.string.ux) ->
                holder.employeeImage.load(context.resources.getDrawable(R.drawable.ux))

            context.resources.getString(R.string.tester) ->
                holder.employeeImage.load(context.resources.getDrawable(R.drawable.tester))
        }
    }

    fun removeAt(position: Int) {
        this.items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun getItemAtPosition(position: Int): Employee {
        return this.items[position]
    }

    /**
     * setting updated list
     * */
    fun setList(dataInformation: List<Employee>) {
        this.items = dataInformation as ArrayList<Employee>
        itemsList.addAll(items)
        notifyDataSetChanged()
    }

    /**
     * filter employee list
     * **/
    fun filter(charText: String) {
        var charText = charText
        charText = charText.toLowerCase(Locale.getDefault())
        items.clear()
        if (charText.isEmpty()) {
            items.addAll(itemsList)
        } else {
            for (wp in itemsList) {
                if (wp.name.toLowerCase(Locale.getDefault()).contains(charText)) {
                    items.add(wp)
                }
            }
        }
        notifyDataSetChanged()
    }

    interface ItemClickListener {
        fun onItemClick(employees: Employee, position: Int)
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(
        employee: Employee,
        position: Int,
        listener: EmployeesRecyclerViewAdapter.ItemClickListener
    ) {
        itemView.setOnClickListener {
            listener.onItemClick(employee, position)
        }
    }

    val employeeName: TextView = view.employeeName
    val band: TextView = view.band
    val designation: TextView = view.designation
    val project: TextView = view.project
    val employeeImage: ImageView = view.employeeImage
    val employeeId: TextView = view.employeeId
    val competency: TextView = view.competency


}
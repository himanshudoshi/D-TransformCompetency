package com.techm.competency.view

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.techm.competency.R
import com.techm.competency.adapter.EmployeesRecyclerViewAdapter
import com.techm.competency.application.ApplicationContext.Companion.context
import com.techm.competency.database.Employee
import com.techm.competency.utils.*
import com.techm.competency.viewmodel.EmployeeViewModel
import kotlinx.android.synthetic.main.activity_main.*

class EmployeeActivity : AppCompatActivity(), View.OnClickListener,
    EmployeesRecyclerViewAdapter.ItemClickListener {
    private lateinit var mEmployeeViewModel: EmployeeViewModel
    private lateinit var mEmployeeAdapter: EmployeesRecyclerViewAdapter
    private lateinit var builder: AlertDialog.Builder
    private lateinit var dialog: AlertDialog
    private var isSwipeToRefreshCall: Boolean = false
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title=getString(R.string.competency_title)
        mEmployeeViewModel = ViewModelProvider(this).get(EmployeeViewModel::class.java)
        searchView.queryHint = getString(R.string.search)

        swipeToRefresh.setOnRefreshListener {
            mEmployeeViewModel.getEmployeeInformation()
            swipeToRefresh.isRefreshing=false
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                newText.let { mEmployeeAdapter.filter(it.trimStart()) }
                return true
            }
        })

        employeeList.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        /**
         * Setting blank adapter for initialize
         */
        mEmployeeAdapter = EmployeesRecyclerViewAdapter(ArrayList(), context, this)
        linearLayoutManager = LinearLayoutManager(this)
        employeeList.layoutManager = linearLayoutManager
        employeeList.adapter = mEmployeeAdapter

        val swipeHandler = object : SwipeToDeleteCallback(context) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                var mModelEmployeeInformation =
                    mEmployeeAdapter.getItemAtPosition(viewHolder.adapterPosition)
                mEmployeeAdapter.removeAt(viewHolder.adapterPosition)
                mEmployeeViewModel.deleteEmployee(mModelEmployeeInformation)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(employeeList)

        /**
         * API Live data observer
         * */
        mEmployeeViewModel.mEmployeeInformationData.observe(this, Observer {
            mEmployeeAdapter.setList(it)
        })
    }

    override fun onClick(v: View?) {

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    /**
     *  Handle action bar item clicks here
     * */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_add -> {
                var intent = Intent(this, AddEmployeeActivity::class.java)
                intent.putExtra(Constant.openFor,0)
                startActivity(intent)
                //   findNavController().navigate(R.id.action_EmployeeInformation_to_AddEmployee)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onItemClick(employee: Employee, position: Int) {
        var intent = Intent(this, AddEmployeeActivity::class.java)
        intent.putExtra(Constant.openFor,1)
        intent.putExtra(Constant.data,employee.id)
        startActivity(intent)
    }


}

package com.techm.competency.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * This class for employee Data
 * */
@Entity(tableName = "employee_data_table")
class Employee(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var name: String,
    var band: String,
    var designation: String,
    var employeeId: String,
    var competency: String,
    var project: String
)
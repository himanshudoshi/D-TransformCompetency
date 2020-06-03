package com.techm.competency.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "project_data_table")
class Project(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var projectName: String
)
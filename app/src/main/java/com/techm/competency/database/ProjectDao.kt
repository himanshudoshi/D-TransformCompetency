package com.techm.competency.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.techm.competency.model.ProjectModel

/**
 * In this interface have all queries fo room database
 */
@Dao
interface ProjectDao {
    @Query("SELECT * FROM project_data_table")
    fun getAllProject(): LiveData<List<Project>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProject(project: Project): Long

}
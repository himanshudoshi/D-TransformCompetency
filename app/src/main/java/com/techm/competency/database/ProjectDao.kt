package com.techm.competency.database

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Interface for Project Queries
 */
@Dao
interface ProjectDao {
    @Query("SELECT * FROM project_data_table")
    fun getAllProject(): LiveData<List<Project>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProject(project: Project): Long

    @Delete
    suspend fun delete(project:Project): Int
}
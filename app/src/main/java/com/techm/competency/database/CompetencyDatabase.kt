package com.techm.competency.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * This class is creating for room database
 */
@Database(entities = [Employee::class, Project::class], version = 3)
abstract class CompetencyDatabase : RoomDatabase() {

    abstract fun getEmployeeDao(): EmployeeDao
    abstract fun getProjectDao(): ProjectDao

    companion object {
        @Volatile
        private var instance: CompetencyDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            CompetencyDatabase::class.java, "employee_database.db"
        )
            .fallbackToDestructiveMigration()//we need it when we increase version number
            .build()
    }
}
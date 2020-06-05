package com.techm.competency

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.techm.competency.database.CompetencyDatabase
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
abstract class CompetencyDatabaseTest {
    lateinit var appDatabase: CompetencyDatabase
    private var appContext = InstrumentationRegistry.getInstrumentation().targetContext
    @Before
    fun initDb() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            appContext,
            CompetencyDatabase::class.java)
            .build()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        appDatabase.close()
    }
}
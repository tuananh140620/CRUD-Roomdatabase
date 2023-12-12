package com.example.crud_roomdatabase

import android.app.Application
import com.example.crud_roomdatabase.data.roomdatabase.StudentRoomDatabase
import com.example.crud_roomdatabase.repository.StudentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class StudentsApplication : Application() {

    private val database by lazy { StudentRoomDatabase.getDatabase(this) }
    val repository by lazy { StudentRepository(database.studentDao()) }
}
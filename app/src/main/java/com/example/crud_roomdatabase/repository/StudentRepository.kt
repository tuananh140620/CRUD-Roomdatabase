package com.example.crud_roomdatabase.repository

import androidx.annotation.WorkerThread
import com.example.crud_roomdatabase.data.model.Student
import com.example.crud_roomdatabase.data.roomdatabase.StudentDao
import kotlinx.coroutines.flow.Flow

class StudentRepository(
    private val studentDao: StudentDao
) {
    val allStudents: Flow<List<Student>> = studentDao.getAll()

    fun insert(student: Student) {
        studentDao.insert(student)
    }

    fun delete(student: Student) {
        studentDao.delete(student)
    }

    fun update(student: Student) {
        studentDao.update(student)
    }

    fun searchStudentsByName(searchQuery: String): Flow<List<Student>> {
        return studentDao.searchStudentsByName(searchQuery)
    }

    val listBookmark:Flow<List<Student>> = studentDao.getListFavorite()
}
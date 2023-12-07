package com.example.crud_roomdatabase.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope

import com.example.crud_roomdatabase.data.model.Student
import com.example.crud_roomdatabase.repository.StudentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentViewModel(
    private val repository: StudentRepository
) : ViewModel() {
    val allStudents: LiveData<List<Student>> = repository.allStudents.asLiveData()

    fun insert(student: Student) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(student)
    }

    fun delete(student: Student) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(student)
    }

    fun update(student: Student) = viewModelScope.launch(Dispatchers.IO) {
        try {
            repository.update(student)
            Log.d("UpdateStudent", "Update successful")
        } catch (e: Exception) {
            Log.e("UpdateStudent", "Error updating student", e)
        }
    }
}
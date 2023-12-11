package com.example.crud_roomdatabase.data.roomdatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.crud_roomdatabase.data.model.Student
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    @Query("SELECT * FROM students ORDER BY name ASC")
    fun getAll(): Flow<List<Student>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(student: Student)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(students: List<Student>)

    @Query("DELETE FROM students")
    fun deleteAll()

    @Delete
    fun delete(student: Student)

    @Update
    fun update(student: Student)
    @Query("SELECT * FROM students WHERE name LIKE '%' || :searchQuery || '%' ORDER BY name ASC")
    fun searchStudentsByName(searchQuery: String): Flow<List<Student>>


}
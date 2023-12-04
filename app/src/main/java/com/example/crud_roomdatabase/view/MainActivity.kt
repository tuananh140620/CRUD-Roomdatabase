package com.example.crud_roomdatabase.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crud_roomdatabase.R
import com.example.crud_roomdatabase.StudentsApplication
import com.example.crud_roomdatabase.adapter.StudentListAdapter
import com.example.crud_roomdatabase.databinding.ActivityMainBinding
import com.example.crud_roomdatabase.bottomsheet.BottomSheetDialogInsert
import com.example.crud_roomdatabase.bottomsheet.DetailBottomSheetFragment
import com.example.crud_roomdatabase.callback.OnItemClickListener
import com.example.crud_roomdatabase.data.model.Student
import com.example.crud_roomdatabase.viewmodel.StudentViewModel
import com.example.crud_roomdatabase.viewmodel.StudentViewModelFactory

class MainActivity : AppCompatActivity(),OnItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private val adapter = StudentListAdapter(this)

    private val studentViewModel: StudentViewModel by viewModels {
        StudentViewModelFactory((application as StudentsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setStatusBarColor()
        setAdapterStudent()
        insertStudent()
    }

    private fun setAdapterStudent() {
        binding.recyclerview.setItemViewCacheSize(20)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        studentViewModel.allStudents.observe(this) { studentList ->
            studentList.forEach { student ->
                studentViewModel.insert(student)
            }
            adapter.setStudentList(studentList)
        }
        binding.recyclerview.adapter = adapter

    }

    private fun setStatusBarColor() {
        val window: Window = this.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.C688EC)
    }

    private fun insertStudent(){
        binding.addStudent.setOnClickListener {
            val bottomSheet = BottomSheetDialogInsert()
            bottomSheet.show(supportFragmentManager,"Insert Student")
        }
    }

    override fun onItemClick(student: Student) {
        val bottomSheetFragment = DetailBottomSheetFragment.newInstance(student)
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
    }
}
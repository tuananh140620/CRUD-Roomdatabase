package com.example.crud_roomdatabase.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crud_roomdatabase.StudentsApplication
import com.example.crud_roomdatabase.view.adapter.StudentListAdapter
import com.example.crud_roomdatabase.view.bottomsheet.DetailBottomSheetFragment
import com.example.crud_roomdatabase.view.bottomsheet.UpdateBottomSheetFragment
import com.example.crud_roomdatabase.callback.OnItemClickListener
import com.example.crud_roomdatabase.data.model.Student
import com.example.crud_roomdatabase.databinding.ActivitySearchBinding
import com.example.crud_roomdatabase.viewmodel.StudentViewModel
import com.example.crud_roomdatabase.viewmodel.StudentViewModelFactory

class SearchActivity : AppCompatActivity() {


    private lateinit var binding:ActivitySearchBinding
    private lateinit var adapter: StudentListAdapter
    private val viewModel: StudentViewModel by viewModels {
        StudentViewModelFactory((application as StudentsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        searchStudent()
        onBack()


    }

    private fun onBack() {
        binding.cvBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun searchStudent() {
        adapter = StudentListAdapter(object : OnItemClickListener {
            override fun onItemClick(student: Student) {
                val bottomDetailSheetFragment = DetailBottomSheetFragment.newInstance(student)
                bottomDetailSheetFragment.show(supportFragmentManager, bottomDetailSheetFragment.tag)
            }

            override fun onUpdateItemClick(student: Student) {
                val bottomSheetUpdateFragment = UpdateBottomSheetFragment.newInstance(student)
                bottomSheetUpdateFragment.show(supportFragmentManager,bottomSheetUpdateFragment.tag)
            }
        },viewModel)

        binding.rvListSearch.layoutManager = LinearLayoutManager(this)
        binding.rvListSearch.adapter = adapter

        binding.edSearch.addTextChangedListener { editable ->
            val searchQuery = editable.toString().trim()
            viewModel.searchStudentsByName(searchQuery).observe(this) { students ->
                adapter.setStudentList(students)
            }
        }
    }
}

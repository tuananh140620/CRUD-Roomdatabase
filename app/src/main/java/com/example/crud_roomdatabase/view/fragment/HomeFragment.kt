package com.example.crud_roomdatabase.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crud_roomdatabase.R
import com.example.crud_roomdatabase.StudentsApplication
import com.example.crud_roomdatabase.view.adapter.StudentListAdapter
import com.example.crud_roomdatabase.view.bottomsheet.BottomSheetDialogInsert
import com.example.crud_roomdatabase.view.bottomsheet.DetailBottomSheetFragment
import com.example.crud_roomdatabase.view.bottomsheet.UpdateBottomSheetFragment
import com.example.crud_roomdatabase.callback.OnItemClickListener
import com.example.crud_roomdatabase.data.model.Student
import com.example.crud_roomdatabase.databinding.FragmentHomeBinding
import com.example.crud_roomdatabase.view.activity.SearchActivity
import com.example.crud_roomdatabase.viewmodel.StudentViewModel
import com.example.crud_roomdatabase.viewmodel.StudentViewModelFactory
import com.google.android.material.snackbar.Snackbar


class HomeFragment : Fragment(), OnItemClickListener {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: StudentListAdapter

    private val studentViewModel: StudentViewModel by viewModels {
        StudentViewModelFactory((requireActivity().application as StudentsApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        setStatusBarColor()
        setAdapterStudent()
        insertStudent()
        deleteStudentItemTouchHelper()
        searchAllStudent()

        return binding.root
    }


    private fun setAdapterStudent() {
        adapter = StudentListAdapter(this,studentViewModel)
        binding.recyclerview.setItemViewCacheSize(20)
        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        studentViewModel.allStudents.observe(requireActivity()) { studentList ->
            studentList.forEach { student ->
                studentViewModel.insert(student)
            }
            adapter.setStudentList(studentList)
        }
        binding.recyclerview.adapter = adapter


    }

    private fun deleteStudentItemTouchHelper() {
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val student = adapter.students[position]

                val deleteButton = Snackbar.make(
                    binding.root,
                    "Delete ${student.name}?",
                    Snackbar.LENGTH_LONG
                )
                deleteButton.setAction("Delete") {
                    adapter.removeStudent(position)
                    studentViewModel.delete(student)
                }
                deleteButton.show()
            }
        })

        itemTouchHelper.attachToRecyclerView(binding.recyclerview)
    }

    private fun setStatusBarColor() {
        val window: Window = requireActivity().window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = requireContext().getColor(R.color.C688EC)
    }

    private fun insertStudent() {
        binding.addStudent.setOnClickListener {
            val bottomSheet = BottomSheetDialogInsert()
            bottomSheet.show(parentFragmentManager, bottomSheet.tag)
        }
    }

    override fun onItemClick(student: Student) {
        val bottomDetailSheetFragment = DetailBottomSheetFragment.newInstance(student)
        bottomDetailSheetFragment.show(parentFragmentManager, bottomDetailSheetFragment.tag)
    }

    override fun onUpdateItemClick(student: Student) {
        val bottomSheetUpdateFragment = UpdateBottomSheetFragment.newInstance(student)
        bottomSheetUpdateFragment.show(parentFragmentManager, bottomSheetUpdateFragment.tag)
    }

    private fun searchAllStudent() {
        binding.cvSearch.setOnClickListener {
            startActivity(Intent(context, SearchActivity::class.java))
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
            }
    }
}
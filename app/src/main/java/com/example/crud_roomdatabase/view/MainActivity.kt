package com.example.crud_roomdatabase.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.Window
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
import com.google.android.material.snackbar.Snackbar

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
        // Tạo một ItemTouchHelper và gán cho RecyclerView
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                // Không cần xử lý khi di chuyển item
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // Xử lý khi item bị vuốt
                val position = viewHolder.adapterPosition
                val student = adapter.students[position]

                // Hiển thị nút delete bên phải và xử lý khi nút được nhấn
                val deleteButton = Snackbar.make(
                    binding.root,
                    "Delete ${student.name}?",
                    Snackbar.LENGTH_LONG
                )
                deleteButton.setAction("Delete") {
                    // Xóa sinh viên từ danh sách khi nút Delete được nhấn
                    adapter.removeStudent(position)
                    // Thực hiện xóa trong ViewModel hoặc database nếu cần
                    studentViewModel.delete(student)
                }
                deleteButton.show()
            }
        })

        // Gán ItemTouchHelper cho RecyclerView
        itemTouchHelper.attachToRecyclerView(binding.recyclerview)

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
            Log.d("add", "Huhu")
        }
    }

    override fun onItemClick(student: Student) {
        val bottomSheetFragment = DetailBottomSheetFragment.newInstance(student)
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
    }
}
package com.example.crud_roomdatabase.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.crud_roomdatabase.callback.OnItemClickListener
import com.example.crud_roomdatabase.data.model.Student
import com.example.crud_roomdatabase.databinding.ItemStudentBinding

class StudentListAdapter(
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<StudentViewHolder>() {

    var students = mutableListOf<Student>()
    private var isDeleteButtonVisible = false


    fun setStudentList(newStudents: List<Student>) {
        students.clear()
        students.addAll(newStudents)
        notifyItemChanged(0, itemCount)
    }
    fun removeStudent(position: Int) {
        if (position < students.size) {
            students.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemStudentBinding.inflate(inflater, parent, false)
        return StudentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return students.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        val binding = holder.binding

        Glide.with(holder.itemView.context)
            .load("file:///android_${student.avatar}")
            .into(holder.binding.imageView)

        binding.tvName.text = student.name
        binding.tvClassName.text = student.className
        binding.tvAge.text = "Age: ${student.age}"
        binding.tvGpa.text = "GPA: ${student.gpa}"

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(
                students[position]
            )
        }
    }
}

class StudentViewHolder(val binding: ItemStudentBinding) : RecyclerView.ViewHolder(binding.root)

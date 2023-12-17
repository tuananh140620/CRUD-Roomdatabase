package com.example.crud_roomdatabase.view.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.crud_roomdatabase.data.model.Student
import com.example.crud_roomdatabase.databinding.BottomSheetDialogInsertBinding
import com.example.crud_roomdatabase.viewmodel.StudentViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomSheetDialogInsert : BottomSheetDialogFragment() {
    private lateinit var binding: BottomSheetDialogInsertBinding

    private val studentViewModel: StudentViewModel by lazy {
        ViewModelProvider(requireActivity())[StudentViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetDialogInsertBinding.inflate(inflater, container, false)

        insertStudent()

        return binding.root
    }

    private fun insertStudent() {
        binding.btnInsert.setOnClickListener {
            val studentName = binding.etName.text.toString()
            val studentClass = binding.etClass.text.toString()
            val studentAge = binding.etAge.text.toString().toInt()
            val studentGpa = binding.etGpa.text.toString().toDouble()

            if (studentName.isNotEmpty() && studentClass.isNotEmpty()) {
                val newStudent =
                    Student(
                        avatar = "asset/avatar1.jpg",
                        name = studentName,
                        className = studentClass,
                        age = studentAge,
                        gpa = studentGpa
                    )
                studentViewModel.insert(newStudent)
                dismiss()
            } else {
                Toast.makeText(requireContext(), "Invalid input", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
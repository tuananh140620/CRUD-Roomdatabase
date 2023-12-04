package com.example.crud_roomdatabase.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.crud_roomdatabase.CLICK_DETAIL_STUDENT
import com.example.crud_roomdatabase.data.model.Student
import com.example.crud_roomdatabase.databinding.BottomSheetDetailBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DetailBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: BottomSheetDetailBinding

    companion object {
        fun newInstance(student: Student): DetailBottomSheetFragment {
            val fragment = DetailBottomSheetFragment()

            val bundle = Bundle().apply {
                putParcelable(CLICK_DETAIL_STUDENT, student)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetDetailBinding.inflate(inflater, container, false)

        val student = arguments?.getParcelable<Student>(CLICK_DETAIL_STUDENT)

        binding.tvName.text = student!!.name
        binding.tvClass.text = student.className
        binding.tvAge.text = student.age.toString()
        binding.tvGpa.text = student.gpa.toString()

        return binding.root
    }
}
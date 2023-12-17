package com.example.crud_roomdatabase.view.bottomsheet

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.crud_roomdatabase.CLICK_DETAIL_STUDENT
import com.example.crud_roomdatabase.R
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
        private inline fun <reified T : Parcelable> Bundle.getParcelableOrNull(key: String): T? =
            getParcelable(key)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetDetailBinding.inflate(inflater, container, false)

        val student = arguments?.getParcelableOrNull<Student>(CLICK_DETAIL_STUDENT)

        binding.tvName.text = getString(R.string.name_label, student?.name ?: "")
        binding.tvClass.text = getString(R.string.class_label, student?.className ?: "")
        binding.tvAge.text = getString(R.string.age_label, student?.age ?: "")
        binding.tvGpa.text = getString(R.string.gpa_label, student?.gpa ?: "")


        return binding.root
    }
}
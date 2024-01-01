package com.example.crud_roomdatabase.view.bottomsheet

import android.os.Bundle
import android.os.Parcelable
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.crud_roomdatabase.CLICK_UPDATE_STUDENT
import com.example.crud_roomdatabase.StudentsApplication
import com.example.crud_roomdatabase.data.model.Student
import com.example.crud_roomdatabase.databinding.BottomSheetUpdateBinding
import com.example.crud_roomdatabase.viewmodel.StudentViewModel
import com.example.crud_roomdatabase.viewmodel.StudentViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class UpdateBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: BottomSheetUpdateBinding

    private val studentViewModel: StudentViewModel by viewModels {
        StudentViewModelFactory((requireActivity().application as StudentsApplication).repository)
    }


    companion object {
        fun newInstance(student: Student): UpdateBottomSheetFragment {
            val fragment = UpdateBottomSheetFragment()

            val bundle = Bundle().apply {
                putParcelable(CLICK_UPDATE_STUDENT, student)
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
        binding = BottomSheetUpdateBinding.inflate(inflater, container, false)

        displayDetailStudent()

        return binding.root
    }

    private fun displayDetailStudent() {
        val student = arguments?.getParcelableOrNull<Student>(CLICK_UPDATE_STUDENT)
        binding.etName.text = Editable.Factory.getInstance().newEditable(student?.name)
        binding.etClass.text = Editable.Factory.getInstance().newEditable(student?.className)
        binding.etAge.text = Editable.Factory.getInstance().newEditable(student?.age.toString())
        binding.etGpa.text = Editable.Factory.getInstance().newEditable(student?.gpa.toString())
        binding.btnUpdate.setOnClickListener {
            val updateStudent =
                Student(
                    id = student?.id ?: 0,
                    avatar = "asset/avatar1.jpg",
                    name = binding.etName.text.toString(),
                    className = binding.etClass.text.toString(),
                    age = binding.etAge.text.toString().toInt(),
                    gpa = binding.etGpa.text.toString().toDouble(),

                    )
            studentViewModel.update(updateStudent)
            Toast.makeText(requireContext(), "Update successful", Toast.LENGTH_SHORT).show()
            dismiss()
        }
    }

}
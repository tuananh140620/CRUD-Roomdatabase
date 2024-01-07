package com.example.crud_roomdatabase.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crud_roomdatabase.StudentsApplication
import com.example.crud_roomdatabase.callback.OnItemClickListener
import com.example.crud_roomdatabase.data.model.Student
import com.example.crud_roomdatabase.databinding.FragmentBookmarkBinding
import com.example.crud_roomdatabase.view.adapter.StudentListAdapter
import com.example.crud_roomdatabase.view.bottomsheet.DetailBottomSheetFragment
import com.example.crud_roomdatabase.view.bottomsheet.UpdateBottomSheetFragment
import com.example.crud_roomdatabase.viewmodel.StudentViewModel
import com.example.crud_roomdatabase.viewmodel.StudentViewModelFactory

class BookmarkFragment : Fragment(), OnItemClickListener {
    private lateinit var binding: FragmentBookmarkBinding
    private lateinit var adapter: StudentListAdapter
    private val viewModel: StudentViewModel by viewModels {
        StudentViewModelFactory((requireActivity().application as StudentsApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookmarkBinding.inflate(layoutInflater)

        setData()

        return binding.root
    }

    private fun setData() {
        adapter = StudentListAdapter(this, viewModel)
        binding.rvBookmark.layoutManager = LinearLayoutManager(context)
        viewModel.allBookmark.observe(requireActivity()) { bookmarkList ->
            adapter.setStudentList(bookmarkList)
        }
        binding.rvBookmark.adapter = adapter
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BookmarkFragment().apply {
            }
    }

    override fun onItemClick(student: Student) {
        val bsDetail = DetailBottomSheetFragment.newInstance(student)
        bsDetail.show(parentFragmentManager, bsDetail.tag)
    }

    override fun onUpdateItemClick(student: Student) {
        val bsUpdate = UpdateBottomSheetFragment.newInstance(student)
        bsUpdate.show(parentFragmentManager, bsUpdate.tag)
    }
}
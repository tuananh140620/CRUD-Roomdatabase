package com.example.crud_roomdatabase.callback

import com.example.crud_roomdatabase.data.model.Student

interface OnItemClickListener {
    fun onItemClick(student: Student)
    fun onUpdateItemClick(student: Student)
}
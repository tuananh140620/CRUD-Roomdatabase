package com.example.crud_roomdatabase.data.model

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.text.Editable
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class Student(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "avatar") val avatar: String?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "age") val age: Int,
    @ColumnInfo(name = "className") val className: String?,
    @ColumnInfo(name = "gpa") val gpa: Double,
    @ColumnInfo(name = "favorite")var favorite: Boolean = false,
) : Parcelable {
    @RequiresApi(Build.VERSION_CODES.Q)
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readBoolean()
    )

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(avatar)
        parcel.writeString(name)
        parcel.writeInt(age)
        parcel.writeString(className)
        parcel.writeDouble(gpa)
        parcel.writeBoolean(favorite)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Student> {
        @RequiresApi(Build.VERSION_CODES.Q)
        override fun createFromParcel(parcel: Parcel): Student {
            return Student(parcel)
        }

        override fun newArray(size: Int): Array<Student?> {
            return arrayOfNulls(size)
        }
    }
}


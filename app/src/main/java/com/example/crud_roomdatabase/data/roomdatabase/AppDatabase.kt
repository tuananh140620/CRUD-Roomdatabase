package com.example.crud_roomdatabase.data.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.crud_roomdatabase.data.model.Student
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Student::class], version = 3, exportSchema = true)
abstract class StudentRoomDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao

    companion object {
        @Volatile
        private var INSTANCE: StudentRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): StudentRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StudentRoomDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(StudentDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class StudentDatabaseCallback(
            private val scope: CoroutineScope
        ) : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
//                        populateDatabase(database.studentDao())
                    }
                }

            }

            fun populateDatabase(studentDao: StudentDao) {
//                studentDao.deleteAll()

                val students = listOf(
                    Student(1, "asset/avatar1.jpg", "Nguyễn Tuấn Anh", 23, "HE140888", 9.6),
                    Student(2, "asset/avatar1.jpg", "Nguyễn Văn B", 25, "HE140889", 8.5),
                    Student(3, "asset/avatar1.jpg", "Trần Thị C", 22, "HE140890", 9.0),
                    Student(4, "asset/avatar1.jpg", "Lê Văn D", 24, "HE140891", 7.8),
                    Student(5, "asset/avatar1.jpg", "Phạm Thị E", 23, "HE140892", 8.2),
                    Student(6, "asset/avatar1.jpg", "Hoàng Văn F", 26, "HE140893", 8.9),
                    Student(7, "asset/avatar1.jpg", "Vũ Thị G", 21, "HE140894", 9.5),
                    Student(8, "asset/avatar1.jpg", "Đặng Văn H", 22, "HE140895", 7.5),
                    Student(9, "asset/avatar1.jpg", "Nguyễn Thị I", 25, "HE140896", 8.7),
                    Student(10, "asset/avatar1.jpg", "Trần Văn K", 23, "HE140897", 9.1),
                    Student(11, "asset/avatar1.jpg", "Lê Thị L", 24, "HE140898", 7.0),
                    Student(12, "asset/avatar1.jpg", "Phạm Văn M", 22, "HE140899", 8.3),
                    Student(13, "asset/avatar1.jpg", "Trần Thị N", 23, "HE140900", 7.9),
                    Student(14, "asset/avatar1.jpg", "Nguyễn Văn P", 24, "HE140901", 9.2),
                    Student(15, "asset/avatar1.jpg", "Lê Thị Q", 21, "HE140902", 8.0),
                    Student(16, "asset/avatar1.jpg", "Đỗ Văn R", 25, "HE140903", 8.6),
                    Student(17, "asset/avatar1.jpg", "Mai Thị S", 23, "HE140904", 7.7),
                    Student(18, "asset/avatar1.jpg", "Vũ Văn T", 26, "HE140905", 9.4),
                    Student(19, "asset/avatar1.jpg", "Phan Thị U", 22, "HE140906", 8.8),
                    Student(20, "asset/avatar1.jpg", "Lý Văn V", 24, "HE140907", 7.2),
                    Student(21, "asset/avatar1.jpg", "Hoàng Thị X", 25, "HE140908", 8.1)
                )
                studentDao.insertAll(students)
            }
        }


    }
}



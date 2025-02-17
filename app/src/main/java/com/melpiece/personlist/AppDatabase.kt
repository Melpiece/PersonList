package com.melpiece.personlist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            return instance ?: synchronized(this){
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java, "roomre.db"
                )
                    .build()
                    .also { instance = it }
            }
        }
    }
}



package com.autocheck.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class,Checklist::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun checklistDao(): ChecklistDao
}
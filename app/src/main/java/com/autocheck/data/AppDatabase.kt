package com.autocheck.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class,Checklist::class,Vehicle::class,Garage::class], version = 5   )
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun checklistDao(): ChecklistDao
    abstract fun garageDao(): GarageDao
    abstract fun vehicleDao(): VehicleDao
}
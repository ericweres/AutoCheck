package com.autocheck.data

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Die [AppDatabase] Klasse repräsentiert die Room-Datenbank für die AutoCheck-Anwendung.
 *
 * @Database Annotation definiert die Entitäten (User, Checklist, Vehicle, Garage) und die Versionsnummer der Datenbank.
 * Jede Entität wird durch eine entsprechende DAO-Schnittstelle (UserDao, ChecklistDao, VehicleDao, GarageDao) unterstützt.
 */
@Database(entities = [User::class, Checklist::class, Vehicle::class, Garage::class], version = 7)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun checklistDao(): ChecklistDao
    abstract fun garageDao(): GarageDao
    abstract fun vehicleDao(): VehicleDao
}

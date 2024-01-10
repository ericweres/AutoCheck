package com.autocheck.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CarDao {
    @Query("SELECT * FROM Car")
    fun getAllCars(): Flow<List<Car>>

    @Query("SELECT * FROM Car WHERE name = :name")
    suspend fun getCarByName(name: String): Flow<List<Car>>
}
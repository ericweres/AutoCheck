package com.autocheck.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface VehicleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVehicle(vehicle: Vehicle)
    @Query("SELECT * FROM Vehicle")
    fun getAllVehicles(): Flow<List<Vehicle>>

    @Query("SELECT * FROM Vehicle WHERE id IN (:ids)")
    suspend fun getVehiclesByIds(ids: List<Int>): List<Vehicle>

    @Query("SELECT * FROM Vehicle WHERE name = :name")
    fun getCarByName(name: String): Flow<List<Vehicle>>

    @Query("SELECT * FROM Vehicle WHERE id = :id")
    fun getCarById(id: Int): Flow<Vehicle>
}
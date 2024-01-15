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
    suspend fun getAllVehicles(): List<Vehicle>

    @Query("SELECT * FROM Vehicle WHERE id IN (:ids)")
    suspend fun getVehiclesByIds(ids: List<Int>): List<Vehicle>

    @Query("SELECT * FROM Vehicle WHERE name LIKE '%' || :name || '%'")
    suspend fun getVehiclesByName(name: String): List<Vehicle>

    @Query("SELECT * FROM Vehicle WHERE name LIKE '%' || :name || '%' AND type in (:type)")
    suspend fun getVehiclesByNameAndType(name: String, type: List<String>): List<Vehicle>


    @Query("SELECT * FROM Vehicle WHERE id = :id")
    suspend fun getVehicleById(id: Int): Vehicle?
}
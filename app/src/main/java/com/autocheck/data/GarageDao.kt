package com.autocheck.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface GarageDao {
    @Insert
    suspend fun insertGarage(garage: Garage)

    @Update
    suspend fun updateGarage(garage: Garage)

    @Query("SELECT * FROM garage")
    fun getAllGarage(): Flow<List<Garage>>

    @Query("SELECT * FROM garage WHERE userId = :userId")
    suspend fun getGarageByUserId(userId: Int): List<Garage>

    @Query("DELETE FROM garage WHERE userId = :userId AND carId = :carId")
    suspend fun removeFromGarage(userId: Int, carId: Int)
}

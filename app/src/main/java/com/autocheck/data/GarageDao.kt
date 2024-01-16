package com.autocheck.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Die [GarageDao] Schnittstelle definiert die Datenbankzugriffsmethoden für die "Garage"-Entität.
 */
@Dao
interface GarageDao {

    /**
     * Fügt eine neue Garage in die Datenbank ein.
     *
     * @param garage Die einzufügende Garage.
     */
    @Insert
    suspend fun insertGarage(garage: Garage)

    /**
     * Aktualisiert eine vorhandene Garage in der Datenbank.
     *
     * @param garage Die zu aktualisierende Garage.
     */
    @Update
    suspend fun updateGarage(garage: Garage)

    /**
     * Ruft alle Garagen aus der Datenbank ab.
     *
     * @return Eine [Flow]-Instanz, die eine Liste von Garagen repräsentiert.
     */
    @Query("SELECT * FROM garage")
    fun getAllGarage(): Flow<List<Garage>>

    /**
     * Ruft alle Garagen eines bestimmten Benutzers anhand seiner ID ab.
     *
     * @param userId Die ID des Benutzers.
     * @return Eine Liste von Garagen des angegebenen Benutzers.
     */
    @Query("SELECT * FROM garage WHERE userId = :userId")
    suspend fun getGarageByUserId(userId: Int): List<Garage>

    /**
     * Entfernt ein Fahrzeug aus der Garage eines bestimmten Benutzers.
     *
     * @param userId Die ID des Benutzers.
     * @param carId Die ID des Fahrzeugs, das aus der Garage entfernt wird.
     */
    @Query("DELETE FROM garage WHERE userId = :userId AND carId = :carId")
    suspend fun removeFromGarage(userId: Int, carId: Int)
}

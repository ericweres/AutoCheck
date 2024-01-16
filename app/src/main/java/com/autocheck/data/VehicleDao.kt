package com.autocheck.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Die [VehicleDao] Schnittstelle definiert die Datenbankzugriffsmethoden für die "Vehicle"-Entität.
 */
@Dao
interface VehicleDao {

    /**
     * Fügt ein neues Fahrzeug in die Datenbank ein oder ersetzt ein vorhandenes, falls es bereits existiert.
     *
     * @param vehicle Das einzufügende oder zu ersetzende Fahrzeug.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVehicle(vehicle: Vehicle)

    /**
     * Ruft alle Fahrzeuge aus der Datenbank ab.
     *
     * @return Eine Liste aller Fahrzeuge in der Datenbank.
     * TODO: wird noch implementiert
     */
    @Suppress("UNUSED")
    @Query("SELECT * FROM Vehicle")
    suspend fun getAllVehicles(): List<Vehicle>

    /**
     * Ruft Fahrzeuge anhand ihrer IDs aus der Datenbank ab.
     *
     * @param ids Eine Liste von Fahrzeug-IDs.
     * @return Eine Liste von Fahrzeugen mit den angegebenen IDs.
     */
    @Query("SELECT * FROM Vehicle WHERE id IN (:ids)")
    suspend fun getVehiclesByIds(ids: List<Int>): List<Vehicle>

    /**
     * Ruft Fahrzeuge anhand ihres Namens aus der Datenbank ab, wobei die Suche nach Teilübereinstimmungen erfolgt.
     *
     * @param name Der Teil des Fahrzeugnamens, nach dem gesucht wird.
     * @return Eine Liste von Fahrzeugen, deren Namen die angegebene Zeichenkette enthalten.
     */
    @Query("SELECT * FROM Vehicle WHERE name LIKE '%' || :name || '%'")
    suspend fun getVehiclesByName(name: String): List<Vehicle>

    /**
     * Ruft Fahrzeuge anhand ihres Namens und Typs aus der Datenbank ab, wobei die Suche nach Teilübereinstimmungen erfolgt.
     *
     * @param name Der Teil des Fahrzeugnamens, nach dem gesucht wird.
     * @param type Eine Liste von Fahrzeugtypen, nach denen gesucht wird.
     * @return Eine Liste von Fahrzeugen, deren Namen die angegebene Zeichenkette enthalten und deren Typen in der angegebenen Liste enthalten sind.
     */
    @Query("SELECT * FROM Vehicle WHERE name LIKE '%' || :name || '%' AND type IN (:type)")
    suspend fun getVehiclesByNameAndType(name: String, type: List<String>): List<Vehicle>

    /**
     * Ruft ein Fahrzeug anhand seiner ID aus der Datenbank ab.
     *
     * @param id Die ID des abzurufenden Fahrzeugs.
     * @return Das abgerufene Fahrzeug oder null, wenn kein Fahrzeug mit der angegebenen ID gefunden wurde.
     */
    @Query("SELECT * FROM Vehicle WHERE id = :id")
    suspend fun getVehicleById(id: Int): Vehicle?
}

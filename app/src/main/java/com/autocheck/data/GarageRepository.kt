package com.autocheck.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Die [GarageRepository] Klasse ist verantwortlich für die Koordination von Datenbankzugriffen bezüglich Garagen.
 * Sie wird über Dependency Injection mit einem [GarageDao] instanziiert.
 */
class GarageRepository @Inject constructor(private val garageDao: GarageDao) {

    /**
     * Fügt eine neue Garage in die Datenbank ein.
     *
     * @param garage Die einzufügende Garage.
     */
    suspend fun insertGarage(garage: Garage) {
        garageDao.insertGarage(garage)
    }

    /**
     * Aktualisiert eine vorhandene Garage in der Datenbank.
     *
     * @param garage Die zu aktualisierende Garage.
     * TODO: Implementierung diese Funktion folgt später
     */
    @Suppress("UNUSED")
    suspend fun updateGarage(garage: Garage) {
        garageDao.updateGarage(garage)
    }

    /**
     * Ruft alle Garagen aus der Datenbank ab und liefert sie als [Flow].
     *
     * @return Eine [Flow]-Instanz, die eine Liste von Garagen repräsentiert.
     * TODO: Implementierung diese Funktion folgt später
     */
    @Suppress("UNUSED")
    fun getAllGarage(): Flow<List<Garage>> {
        return garageDao.getAllGarage()
    }

    /**
     * Ruft alle Garagen eines bestimmten Benutzers anhand seiner ID ab.
     *
     * @param userId Die ID des Benutzers.
     * @return Eine Liste von Garagen des angegebenen Benutzers.
     */
    suspend fun getGarageByUserId(userId: Int): List<Garage> {
        return garageDao.getGarageByUserId(userId)
    }

    /**
     * Entfernt ein Fahrzeug aus der Garage eines bestimmten Benutzers.
     *
     * @param userId Die ID des Benutzers.
     * @param carId Die ID des Fahrzeugs, das aus der Garage entfernt wird.
     * TODO: Implementierung diese Funktion folgt später
     */
    @Suppress("UNUSED")
    suspend fun removeFromGarage(userId: Int, carId: Int) {
        garageDao.removeFromGarage(userId, carId)
    }
}

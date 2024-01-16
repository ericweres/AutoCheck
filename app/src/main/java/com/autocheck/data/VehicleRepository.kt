package com.autocheck.data

import javax.inject.Inject

/**
 * Die [VehicleRepository] Klasse ist verantwortlich für die Koordination von Datenbankzugriffen bezüglich Fahrzeugdaten.
 * Sie wird über Dependency Injection mit einem [VehicleDao] instanziiert.
 */
class VehicleRepository @Inject constructor(private val vehicleDao: VehicleDao) {

    /**
     * Fügt ein neues Fahrzeug in die Datenbank ein oder ersetzt ein vorhandenes, falls es bereits existiert.
     *
     * @param vehicle Das einzufügende oder zu ersetzende Fahrzeug.
     */
    suspend fun addVehicle(vehicle: Vehicle) {
        vehicleDao.insertVehicle(vehicle)
    }

    /**
     * Ruft Fahrzeuge anhand ihres Namens aus der Datenbank ab, wobei die Suche nach Teilübereinstimmungen erfolgt.
     *
     * @param name Der Teil des Fahrzeugnamens, nach dem gesucht wird.
     * @return Eine Liste von Fahrzeugen, deren Namen die angegebene Zeichenkette enthalten.
     * TODO: wird noch implementiert
     */
    @Suppress("UNUSED")
    suspend fun getVehiclesByName(name: String): List<Vehicle> {
        return vehicleDao.getVehiclesByName(name)
    }

    /**
     * Ruft Fahrzeuge anhand ihres Namens und Typs aus der Datenbank ab, wobei die Suche nach Teilübereinstimmungen erfolgt.
     *
     * @param name Der Teil des Fahrzeugnamens, nach dem gesucht wird.
     * @param type Eine Liste von Fahrzeugtypen, nach denen gesucht wird.
     * @return Eine Liste von Fahrzeugen, deren Namen die angegebene Zeichenkette enthalten und deren Typen in der angegebenen Liste enthalten sind.
     */
    suspend fun getVehiclesByNameAndType(name: String, type: List<String>): List<Vehicle> {
        return vehicleDao.getVehiclesByNameAndType(name, type)
    }

    /**
     * Ruft Fahrzeuge anhand ihrer IDs aus der Datenbank ab.
     *
     * @param ids Eine Liste von Fahrzeug-IDs.
     * @return Eine Liste von Fahrzeugen mit den angegebenen IDs.
     */
    suspend fun getVehiclesByIds(ids: List<Int>): List<Vehicle> {
        return vehicleDao.getVehiclesByIds(ids)
    }

    /**
     * Ruft ein Fahrzeug anhand seiner ID aus der Datenbank ab.
     *
     * @param id Die ID des abzurufenden Fahrzeugs.
     * @return Das abgerufene Fahrzeug oder null, wenn kein Fahrzeug mit der angegebenen ID gefunden wurde.
     */
    suspend fun getVehicleById(id: Int): Vehicle? {
        return vehicleDao.getVehicleById(id)
    }
}

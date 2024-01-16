package com.autocheck.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.autocheck.data.Checklist
import com.autocheck.data.ChecklistRepository
import com.autocheck.data.Garage
import com.autocheck.data.GarageRepository
import com.autocheck.data.Vehicle
import com.autocheck.data.VehicleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel für die Garage-Funktionen in der AutoCheck-App.
 *
 * Diese Klasse ist mit Hilt annotiert, um Abhängigkeiten über Hilt-Dagger zu verwalten.
 * Sie ist verantwortlich für die Verwaltung von Fahrzeugen in der Garage, einschließlich des Abrufs,
 * der Sortierung und des Hinzufügens von Fahrzeugen.
 *
 * @property garageRepository Ein [GarageRepository]-Objekt zur Datenverwaltung für die Garage.
 * @property vehicleRepository Ein [VehicleRepository]-Objekt zur Datenverwaltung für Fahrzeuge.
 * @property checklistRepository Ein [ChecklistRepository]-Objekt zur Datenverwaltung für Checklisten.
 */
@HiltViewModel
class GarageViewModel @Inject constructor(
    private val garageRepository: GarageRepository,
    private val vehicleRepository: VehicleRepository,
    private val checklistRepository: ChecklistRepository
) : ViewModel() {

    // MutableStateFlow für die Liste von Fahrzeugen in der Garage mit zugehörigen Checklisten
    private val _vehicles = MutableStateFlow<List<VehicleWithChecklist>>(emptyList())
    val vehicles: StateFlow<List<VehicleWithChecklist>> = _vehicles.asStateFlow()

    // Enum-Klasse für die Sortieroptionen der Fahrzeuge
    enum class SortType {
        NAME, TYPE
    }

    // Aktuelle Sortieroption für die Fahrzeuge
    var sortType by mutableStateOf(SortType.NAME)

    /**
     * Funktion zum Sortieren der Fahrzeuge in der Garage basierend auf der aktuellen Sortieroption.
     */
    fun sortVehicles() {
        _vehicles.value = when (sortType) {
            SortType.NAME -> vehicles.value.sortedBy { it.vehicle.name }
            SortType.TYPE -> vehicles.value.sortedBy { it.vehicle.type }
        }
    }

    /**
     * Funktion zum Laden der Fahrzeuge in der Garage für einen bestimmten Benutzer.
     *
     * @param userId Die ID des Benutzers, dessen Fahrzeuge geladen werden sollen.
     */
    fun loadVehicles(userId: Int) {
        viewModelScope.launch {
            // Abrufen der Garage-Einträge für den Benutzer
            val listOfGarage = garageRepository.getGarageByUserId(userId)

            // Extrahieren der eindeutigen IDs der Fahrzeuge und Checklisten aus der Garage
            val carIds = listOfGarage.map { it.carId }.distinct()
            val checklistIds = listOfGarage.map { it.checklistId }.distinct()

            // Abrufen aller Fahrzeuge und Checklisten basierend auf den IDs
            val allVehicles = vehicleRepository.getVehiclesByIds(carIds)
            val allChecklists = checklistRepository.getChecklistByIds(checklistIds)

            // Verknüpfen der Fahrzeuge und Checklisten basierend auf den Garage-Einträgen
            val vehiclesWithChecklists = listOfGarage.mapNotNull { garage ->
                val vehicle = allVehicles.find { it.id == garage.carId }
                val checklist = allChecklists.find { it.id == garage.checklistId }
                if (vehicle != null && checklist != null) {
                    VehicleWithChecklist(vehicle, checklist)
                } else {
                    null
                }
            }

            // Aktualisieren des Zustands mit den geladenen Fahrzeugen
            _vehicles.value = vehiclesWithChecklists
        }
    }

    /**
     * Funktion zum Hinzufügen eines Fahrzeugs zur Garage für einen bestimmten Benutzer.
     *
     * @param userId Die ID des Benutzers, dem das Fahrzeug hinzugefügt wird.
     * @param carId Die ID des hinzuzufügenden Fahrzeugs.
     * @param checklistId Die ID der Checkliste für das hinzuzufügende Fahrzeug.
     */
    fun addVehicleToGarage(userId: Int, carId: Int, checklistId: Int) {
        val garageEntry = Garage(userId = userId, carId = carId, checklistId = checklistId)
        viewModelScope.launch {
            // Eintragen des Fahrzeugs in die Garage
            garageRepository.insertGarage(garageEntry)
        }
    }

    /**
     * Funktion zum Hinzufügen von Beispielfahrzeugen.
     *
     * Diese Funktion fügt eine vordefinierte Liste von Beispielfahrzeugen zur Datenbank hinzu.
     */
    fun addSampleVehicles() {
        viewModelScope.launch {
            val vehicles = listOf(
                Vehicle(name = "Mercedes E-Klasse T", type = "car"),
                Vehicle(name = "Kawasaki Ninja H2R", type = "bike"),
                // ... Weitere Fahrzeuge ...
                Vehicle(name = "Fiat Tipo", type = "car"),
            )

            // Hinzufügen der Beispielfahrzeuge zur Datenbank
            vehicles.forEach { vehicle ->
                vehicleRepository.addVehicle(vehicle)
            }
        }
    }
}

/**
 * Datenklasse zur Darstellung eines Fahrzeugs mit zugehöriger Checkliste.
 *
 * @property vehicle Das Fahrzeug.
 * @property checklist Die zugehörige Checkliste.
 */
data class VehicleWithChecklist(
    val vehicle: Vehicle,
    val checklist: Checklist?
)

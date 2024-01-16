package com.autocheck.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.autocheck.data.Vehicle
import com.autocheck.data.VehicleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel für die Darstellung und Verwaltung von Fahrzeugdetails.
 *
 * Diese Klasse ist mit Hilt annotiert, um Abhängigkeiten über Hilt-Dagger zu verwalten.
 * Sie ist verantwortlich für das Abrufen von Fahrzeugdaten anhand der ID und die Bereitstellung
 * der Informationen für die Anzeige.
 *
 * @property vehicleRepository Ein [VehicleRepository]-Objekt zur Datenverwaltung für Fahrzeuge.
 */
@HiltViewModel
class VehicleViewModel @Inject constructor(
    private val vehicleRepository: VehicleRepository
) : ViewModel() {

    // MutableStateFlow für das ausgewählte Fahrzeug
    private val _selectedVehicle = MutableStateFlow(
        Vehicle(-1, "Fehler", "car")
    )
    val selectedVehicle: StateFlow<Vehicle> = _selectedVehicle.asStateFlow()

    /**
     * Funktion zum Abrufen von Fahrzeugdetails anhand der Fahrzeug-ID.
     *
     * @param vehicleId Die ID des abzurufenden Fahrzeugs.
     */
    fun fetchVehicleById(vehicleId: Int) {
        viewModelScope.launch {
            // Fahrzeug anhand der ID aus der Datenbank abrufen
            val vehicle = vehicleRepository.getVehicleById(vehicleId)

            // Überprüfen, ob ein Fahrzeug gefunden wurde
            if (vehicle != null) {
                // Bei erfolgreicher Abfrage: Aktualisieren des MutableStateFlow mit den Fahrzeugdetails
                _selectedVehicle.value = vehicle
            }
        }
    }
}

package com.autocheck.viewmodel

import androidx.compose.runtime.mutableStateListOf
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
 * ViewModel für die Suche in der AutoCheck-App.
 *
 * Diese Klasse ist mit Hilt annotiert, um Abhängigkeiten über Hilt-Dagger zu verwalten.
 * Sie ist verantwortlich für die Verwaltung der Suchfunktion, einschließlich des Suchbegriffs
 * und der Aktualisierung der gefilterten Fahrzeuge basierend auf dem Suchbegriff und Fahrzeugtypen.
 *
 * @property repository Ein [VehicleRepository]-Objekt zur Datenverwaltung für Fahrzeuge.
 */
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: VehicleRepository
) : ViewModel() {

    // MutableStateFlow für den aktuellen Suchbegriff
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    // MutableStateFlow für die gefilterte Liste von Fahrzeugen basierend auf dem Suchbegriff
    private val _filteredVehicles = MutableStateFlow<List<Vehicle>>(emptyList())
    val filteredVehicles: StateFlow<List<Vehicle>> = _filteredVehicles.asStateFlow()

    /**
     * Funktion zur Aktualisierung des Suchbegriffs und der gefilterten Fahrzeuge.
     *
     * @param query Der neue Suchbegriff.
     * @param type Die Liste der Fahrzeugtypen, nach denen gefiltert werden soll.
     */
    fun updateSearchQuery(query: String, type: List<String> ) {
        _searchQuery.value = query
        viewModelScope.launch {
            // Aktualisieren der gefilterten Fahrzeuge basierend auf dem Suchbegriff und den Fahrzeugtypen
            _filteredVehicles.value = repository.getVehiclesByNameAndType(query,type)
        }
    }

    // Initialisierung der Suche mit einem leeren Suchbegriff und den Fahrzeugtypen "car" und "bike"
    init {
        updateSearchQuery("",mutableStateListOf("car","bike"))
    }
}

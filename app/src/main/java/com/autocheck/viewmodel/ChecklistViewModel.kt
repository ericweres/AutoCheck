package com.autocheck.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.autocheck.data.Checklist
import com.autocheck.data.ChecklistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel für die Checkliste-Funktionen in der AutoCheck-App.
 *
 * Diese Klasse ist mit Hilt annotiert, um Abhängigkeiten über Hilt-Dagger zu verwalten.
 * Sie verwaltet die Zustände für die aktuelle Checkliste und die ID der zuletzt gespeicherten Checkliste.
 * Außerdem stellt sie Funktionen zum Laden und Speichern von Checklisten bereit.
 *
 * @property repository Ein [ChecklistRepository]-Objekt zur Datenverwaltung für Checklisten.
 */
@HiltViewModel
class ChecklistViewModel @Inject constructor(
    private val repository: ChecklistRepository
) : ViewModel() {

    // MutableStateFlow für die aktuelle Checkliste
    private val _checklist = MutableStateFlow<Checklist?>(null)
    val checklist: StateFlow<Checklist?> = _checklist.asStateFlow()

    // MutableStateFlow für die ID der zuletzt gespeicherten Checkliste
    private val _savedChecklistId = MutableStateFlow(-1)
    val savedChecklistId: StateFlow<Int> = _savedChecklistId.asStateFlow()

    /**
     * Funktion zum Laden einer Checkliste anhand der Fahrzeug-ID.
     *
     * @param carId Die ID des Fahrzeugs, für das die Checkliste geladen werden soll.
     * TODO: Funktion usage wird später implementiert
     */
    @Suppress ("UNUSED")
    fun loadChecklistByCarId(carId: Int) {
        viewModelScope.launch {
            val loadedChecklist = repository.getChecklistById(carId)
            _checklist.value = loadedChecklist
        }
    }

    /**
     * Funktion zum Speichern einer Checkliste.
     *
     * @param checklist Die zu speichernde Checkliste.
     */
    fun saveChecklist(checklist: Checklist) {
        viewModelScope.launch {
            // Aufruf der Repository-Funktion zum Ein- oder Aktualisieren der Checkliste
            val newId = repository.insertOrUpdateChecklist(checklist)

            // Aktualisierung der Zustände mit der neuen ID und der gespeicherten Checkliste
            _savedChecklistId.value = newId.toInt()
            _checklist.value = checklist
        }
    }
}

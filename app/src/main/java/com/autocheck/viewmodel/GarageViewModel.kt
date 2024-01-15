package com.autocheck.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.autocheck.data.Checklist
import com.autocheck.data.ChecklistRepository
import com.autocheck.data.Vehicle
import com.autocheck.data.VehicleRepository
import com.autocheck.data.Garage
import com.autocheck.data.GarageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GarageViewModel @Inject constructor(
    private val garageRepository: GarageRepository,
    private val vehicleRepository: VehicleRepository,
    private val checklistRepository: ChecklistRepository
) : ViewModel() {

    private val _vehicles = MutableStateFlow<List<VehicleWithChecklist>>(emptyList())
    val vehicles: StateFlow<List<VehicleWithChecklist>> = _vehicles.asStateFlow()


    enum class SortType {
        NAME, TYPE
    }

    var sortType by mutableStateOf(SortType.NAME)

    fun sortVehicles() {
        _vehicles.value = when (sortType) {
            SortType.NAME -> vehicles.value.sortedBy { it.vehicle.name }
            SortType.TYPE -> vehicles.value.sortedBy { it.vehicle.type }
        }
    }
    fun loadVehicles(userId: Int) {
        viewModelScope.launch {
            val listOfGarage = garageRepository.getGarageByUserId(userId)
            val carIds = listOfGarage.map { it.carId }.distinct()
            val checklistIds = listOfGarage.map { it.checklistId }.distinct()

            val allVehicles = vehicleRepository.getVehiclesByIds(carIds)
            val allChecklists = checklistRepository.getChecklistByIds(checklistIds)

            val vehiclesWithChecklists = listOfGarage.mapNotNull { garage ->
                val vehicle = allVehicles.find { it.id == garage.carId }
                val checklist = allChecklists.find { it.id == garage.checklistId }
                if (vehicle != null && checklist != null) {
                    VehicleWithChecklist(vehicle, checklist)
                } else {
                    null
                }
            }

            _vehicles.value = vehiclesWithChecklists
        }
    }

    fun addVehicleToGarage(userId: Int, carId: Int, checklistId: Int) {
        val garageEntry = Garage(userId = userId, carId = carId, checklistId = checklistId)
        viewModelScope.launch {
            garageRepository.insertGarage(garageEntry)
        }
    }

    fun addSampleVehicles() {
        viewModelScope.launch {
            val vehicles = listOf(
                Vehicle(name = "Mercedes E-Klasse T", type = "car"),
                Vehicle(name = "Kawasaki Ninja H2R", type = "bike"),
                Vehicle(name = "Opel Insignia ST", type = "car"),
                Vehicle(name = "Yamaha R1", type = "bike"),
                Vehicle(name = "BMW 520d Touring", type = "car"),
                Vehicle(name = "BMW S1000RR", type = "bike"),
                Vehicle(name = "Porsche Taycan ST", type = "car"),
                Vehicle(name = "BMW 318d Touring", type = "car"),
                Vehicle(name = "Honda Accord", type = "car"),
                Vehicle(name = "Mitsubishi Lancer", type = "car"),
                Vehicle(name = "Jaguar XF ST", type = "car"),
                Vehicle(name = "Audi RS6 Avant", type = "car"),
                Vehicle(name = "Volkswagen Golf", type = "car"),
                Vehicle(name = "Skoda Octavia", type = "car"),
                Vehicle(name = "Renault Talisman", type = "car"),
                Vehicle(name = "Kia Optima ST", type = "car"),
                Vehicle(name = "Hyundai I50 ST", type = "car"),
                Vehicle(name = "Mazda 6", type = "car"),
                Vehicle(name = "Volvo V70", type = "car"),
                Vehicle(name = "Fiat Tipo", type = "car"),
            )

            vehicles.forEach { vehicle ->
                vehicleRepository.addVehicle(vehicle)
            }
        }
    }
}

data class VehicleWithChecklist(
    val vehicle: Vehicle,
    val checklist: Checklist?
)
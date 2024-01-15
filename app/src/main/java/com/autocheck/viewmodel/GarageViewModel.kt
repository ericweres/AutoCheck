package com.autocheck.viewmodel

import android.util.Log
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

    fun loadVehicles(userId: Int) {
        viewModelScope.launch {
            val listOfGarage = garageRepository.getGarageByUserId(userId)
            val carIds = listOfGarage.map { it.carId }
            val checklistIds = listOfGarage.map { it.checklistId }

            val allVehicles = vehicleRepository.getVehiclesByIds(carIds.distinct())
            val allChecklists = checklistRepository.getChecklistByIds(checklistIds.distinct())


            val duplicatedVehicles = carIds.mapNotNull { id ->
                allVehicles.find { vehicle -> vehicle.id == id }
            }
            val carIdToChecklistIdMap = listOfGarage.associate { it.carId to it.checklistId }

            val vehiclesWithChecklists = duplicatedVehicles.map { vehicle ->
                val checklistId = carIdToChecklistIdMap[vehicle.id]
                val checklist = allChecklists.find { it.id == checklistId }
                VehicleWithChecklist(vehicle, checklist)
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
                Vehicle(name = "Mercedes E-Klasse T Modell", type = "car"),
                Vehicle(name = "Audi A4 Avant", type = "bike"),
                Vehicle(name = "Opel Insignia Sports Tourer", type = "car"),
                Vehicle(name = "Mercedes CLS Shooting Brake", type = "bike"),
                Vehicle(name = "BMW 520d Touring", type = "car"),
                Vehicle(name = "Audi A6 Avant", type = "bike"),
                Vehicle(name = "Porsche Taycan Sports Tourismo", type = "car"),
                Vehicle(name = "BMW 318d Touring", type = "car"),
                Vehicle(name = "Honda Accord", type = "car"),
                Vehicle(name = "Mitsubishi Lancer", type = "car"),
                Vehicle(name = "Jaguar XF ST", type = "car"),
                Vehicle(name = "Audi RS6 Avant", type = "car"),
                Vehicle(name = "Volkswagen Golf Variant", type = "car"),
                Vehicle(name = "Skoda Octavia Kombi", type = "car"),
                Vehicle(name = "Renault Talisman Tourer", type = "car"),
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
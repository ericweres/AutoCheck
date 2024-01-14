package com.autocheck.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val vehicleRepository: VehicleRepository
) : ViewModel() {

    private val _vehicles = MutableStateFlow<List<Vehicle>>(emptyList())
    val vehicles: StateFlow<List<Vehicle>> = _vehicles.asStateFlow()

    init {
        loadVehicles()
    }

    private fun loadVehicles() {
        viewModelScope.launch {
            val listOfGarage = garageRepository.getAllGarage().first()
            val carIds = listOfGarage.map { it.carId }
            _vehicles.value = vehicleRepository.getVehiclesByIds(carIds)
        }
    }

    fun addVehicleToGarage(userId: Int, carId: Int) {
        val garageEntry = Garage(userId = userId, carId = carId, checklistId = 1)
        viewModelScope.launch {
            garageRepository.insertGarage(garageEntry)
        }
    }

    private fun addSampleVehicles() {
        viewModelScope.launch {
            val vehicles = listOf(
                Vehicle(name = "Tesla Model S", type = "car"),
                Vehicle(name = "Yamaha YZF", type = "bike"),
                Vehicle(name = "Ford Mustang", type = "car"),
                Vehicle(name = "Harley Davidson", type = "bike"),
                Vehicle(name = "Chevrolet Corvette", type = "car"),
                Vehicle(name = "Ducati Panigale", type = "bike"),
                Vehicle(name = "Porsche 911", type = "car")
            )

            vehicles.forEach { vehicle ->
                vehicleRepository.addVehicle(vehicle)
            }
        }
    }
}

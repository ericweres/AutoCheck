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
        addSampleVehicles()
        addVehicleToGarage(1,1)
        addVehicleToGarage(1,2)
        addVehicleToGarage(1,3)
        addVehicleToGarage(1,4)
        addVehicleToGarage(1,5)
        addVehicleToGarage(1,6)
        addVehicleToGarage(1,7)


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
        val garageEntry = Garage(userId = userId, carId = carId)
        viewModelScope.launch {
            garageRepository.insertGarage(garageEntry)
        }
    }

    private fun addSampleVehicles() {
        viewModelScope.launch {
            val vehicles = listOf(
                Vehicle(name = "Tesla Model S", type = "car", checklist = null),
                Vehicle(name = "Yamaha YZF", type = "bike", checklist = null),
                Vehicle(name = "Ford Mustang", type = "car", checklist = null),
                Vehicle(name = "Harley Davidson", type = "bike", checklist = null),
                Vehicle(name = "Chevrolet Corvette", type = "car", checklist = null),
                Vehicle(name = "Ducati Panigale", type = "bike", checklist = null),
                Vehicle(name = "Porsche 911", type = "car", checklist = null)
            )

            vehicles.forEach { vehicle ->
                vehicleRepository.addVehicle(vehicle)
            }
        }
    }
}

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

@HiltViewModel
class VehicleViewModel @Inject constructor(
    private val vehicleRepository: VehicleRepository
) : ViewModel() {
    private val _selectedVehicle = MutableStateFlow<Vehicle>(
        Vehicle(-1,"Fehler", "car")
    )
    val selectedVehicle: StateFlow<Vehicle> = _selectedVehicle.asStateFlow()

    fun fetchVehicleById(vehicleId: Int) {
        viewModelScope.launch {
            val vehicle = vehicleRepository.getVehicleById(vehicleId)

            if (vehicle != null)
            {
                _selectedVehicle.value = vehicle
            }
        }
    }
}

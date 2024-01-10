package com.autocheck.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.autocheck.data.Garage
import com.autocheck.data.GarageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GarageViewModel @Inject constructor(
    private val repository: GarageRepository
) : ViewModel() {

    fun addCarToGarage(userId: Int, carId: Int) {
        val garageEntry = Garage(userId = userId, carId = carId)
        viewModelScope.launch {
            repository.insertGarage(garageEntry)
        }
    }
}

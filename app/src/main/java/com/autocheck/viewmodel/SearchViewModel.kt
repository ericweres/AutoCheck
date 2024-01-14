package com.autocheck.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.autocheck.data.Vehicle
import com.autocheck.data.VehicleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: VehicleRepository
) : ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _filteredVehicles = MutableStateFlow<List<Vehicle>>(emptyList())
    val filteredVehicles: StateFlow<List<Vehicle>> = _filteredVehicles.asStateFlow()

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        viewModelScope.launch {
            _filteredVehicles.value = repository.getVehiclesByName(query)
        }
    }
}
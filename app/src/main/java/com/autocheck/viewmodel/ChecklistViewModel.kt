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

@HiltViewModel
class ChecklistViewModel @Inject constructor(
    private val repository: ChecklistRepository
) : ViewModel() {

    private val _checklist = MutableStateFlow<Checklist?>(null)
    val checklist: StateFlow<Checklist?> = _checklist.asStateFlow()

    fun loadChecklistByCarId(carId: Int) {
        viewModelScope.launch {
            val loadedChecklist = repository.getChecklistByCarId(carId)
            _checklist.value = loadedChecklist
        }
    }

    fun saveChecklist(checklist: Checklist) = viewModelScope.launch {
        repository.insertOrUpdateChecklist(checklist)
        _checklist.value = checklist
    }

}
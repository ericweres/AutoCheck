package com.autocheck.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.autocheck.data.Checklist
import com.autocheck.data.ChecklistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
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

    private val _savedChecklistId = MutableStateFlow(-1)
    val savedChecklistId: StateFlow<Int> = _savedChecklistId.asStateFlow()


    fun loadChecklistByCarId(carId: Int) {
        viewModelScope.launch {
            val loadedChecklist = repository.getChecklistById(carId)
            _checklist.value = loadedChecklist
        }
    }

    fun saveChecklist(checklist: Checklist) {
        viewModelScope.launch {
            val newId = repository.insertOrUpdateChecklist(checklist)
            _savedChecklistId.value = newId.toInt()
            _checklist.value = checklist

        }
    }
}
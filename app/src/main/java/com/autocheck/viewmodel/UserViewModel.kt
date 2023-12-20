package com.autocheck.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.autocheck.data.User
import com.autocheck.data.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class UserViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    val users: Flow<List<User>> = repository.getAllUsers()
    fun addUser(username: String, email: String, password: String) {
        viewModelScope.launch {
            repository.addUser(username, email, password)
        }
    }
}
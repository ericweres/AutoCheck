package com.autocheck.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.autocheck.data.User
import com.autocheck.data.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.mindrot.jbcrypt.BCrypt
import javax.inject.Inject

@HiltViewModel

class UserViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    val users: Flow<List<User>> = repository.getAllUsers()
    fun addUser(username: String, email: String, password: String) {
        viewModelScope.launch {
            val hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt())
            repository.addUser(username, email, hashedPassword)
        }
    }

    fun loginUser(email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val user = repository.getUserByEmail(email)
            user?.let {
                val doesPasswordMatch = BCrypt.checkpw(password, user.password)
                onResult(doesPasswordMatch)
            } ?: onResult(false)
        }
    }

}
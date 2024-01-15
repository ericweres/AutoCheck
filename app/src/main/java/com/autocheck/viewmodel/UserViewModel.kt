package com.autocheck.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.autocheck.data.User
import com.autocheck.data.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.mindrot.jbcrypt.BCrypt
import javax.inject.Inject

@HiltViewModel

class UserViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {

    private var _userId = MutableStateFlow<Int>(-1)
    val userId: StateFlow<Int> = _userId
    private var _username = MutableStateFlow<String>("Nicht Angemeldet")
    val username: Flow<String> = _username

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
                if (doesPasswordMatch) {
                    _userId.value = user.userId
                    _username.value = user.username
                }
                onResult(doesPasswordMatch)
            } ?: onResult(false)
        }
    }
}
package com.autocheck.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {
    suspend fun addUser(username: String, email: String, password: String) {
        val newUser = User(username = username, email = email, password = password)
        userDao.insert(newUser)
    }

    fun getAllUsers(): Flow<List<User>> = userDao.getAllUsers()
}
package com.autocheck.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAllUsers(): Flow<List<User>>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): Flow<List<User>>

    @Query("SELECT * FROM user WHERE username LIKE :username LIMIT 1")
    suspend fun findByUsername(username: String): User

    @Insert
    suspend fun insert(user: User)

    @Delete
    suspend fun delete(user: User)
}

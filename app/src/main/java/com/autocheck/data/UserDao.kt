package com.autocheck.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAllUsers(): Flow<List<User>>

    @Query("SELECT * FROM user WHERE userId IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): Flow<List<User>>

    @Query("SELECT * FROM user WHERE email LIKE :email LIMIT 1")
    suspend fun findByEmail(email: String): User?

    @Insert
    suspend fun insert(user: User)

    @Delete
    suspend fun delete(user: User)
}

package com.autocheck.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ChecklistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(checklist: Checklist): Long

    @Query("SELECT * FROM Checklist WHERE id = :id")
    suspend fun getById(id: Int): Checklist

    @Query("SELECT * FROM Checklist WHERE id IN (:ids)")
    suspend fun getByIds(ids: List<Int>): List<Checklist>

    @Update
    suspend fun update(checklist: Checklist)

    @Delete
    suspend fun delete(checklist: Checklist)
}
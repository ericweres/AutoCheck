package com.autocheck.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Car(
    @PrimaryKey(autoGenerate = true) val carId: Int = 0,
    @ColumnInfo(name = "name") val name: Int?,
    @ColumnInfo(name = "checklistId") val email: String,
)
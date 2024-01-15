package com.autocheck.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Checklist(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "light") val light: Int?,
    @ColumnInfo(name = "engine") val engine: Int?,
    @ColumnInfo(name = "bumper") val bumper: Int?,
    @ColumnInfo(name = "sensor") val sensor: Int?,
    @ColumnInfo(name = "exterior") val exterior: Int?,
    @ColumnInfo(name = "tires") val tires: Int?,
    @ColumnInfo(name = "rims") val rims: Int?,
    @ColumnInfo(name = "brakes") val brakes: Int?,
    @ColumnInfo(name = "suspension") val suspension: Int?,
    @ColumnInfo(name = "wheel") val wheel: Int?,
    @ColumnInfo(name = "sill") val sill: Int?,
    @ColumnInfo(name = "mirror") val mirror: Int?,
    @ColumnInfo(name = "seat") val seat: Int?,
    @ColumnInfo(name = "infotainment") val infotainment: Int?,
    @ColumnInfo(name = "interior") val interior: Int?,
    @ColumnInfo(name = "exhaust") val exhaust: Int?,
)

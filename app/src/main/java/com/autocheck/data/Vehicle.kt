package com.autocheck.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Die [Vehicle] Datenklasse repräsentiert die Entität "Vehicle" in der SQLite-Datenbank.
 *
 * @property id Die eindeutige ID des Fahrzeugs, automatisch generiert.
 * @property name Der Name des Fahrzeugs.
 * @property type Der Typ des Fahrzeugs.
 */
@Entity
data class Vehicle(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "type") val type: String,
)

package com.autocheck.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Die [Garage] Datenklasse repräsentiert die Entität "Garage" in der SQLite-Datenbank.
 *
 * @property id Die eindeutige ID der Garage, automatisch generiert.
 * @property userId Die ID des Benutzers, dem die Garage zugeordnet ist.
 * @property carId Die ID des Fahrzeugs in der Garage.
 * @property checklistId Die ID der Checkliste, die mit dem Fahrzeug in der Garage verknüpft ist.
 */
@Entity
data class Garage(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "userId") val userId: Int,
    @ColumnInfo(name = "carId") val carId: Int,
    @ColumnInfo(name = "checklistId") val checklistId: Int,
)

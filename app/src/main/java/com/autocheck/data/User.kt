package com.autocheck.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Die [User] Datenklasse repräsentiert die Entität "User" in der Datenbank.
 *
 * @param userId Die eindeutige ID des Benutzers (automatisch generiert).
 * @param username Der Benutzername des Benutzers.
 * @param email Die E-Mail-Adresse des Benutzers.
 * @param password Das Passwort des Benutzers.
 */
@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val userId: Int = 0,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "password") val password: String,
)
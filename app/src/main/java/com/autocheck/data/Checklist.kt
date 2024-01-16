package com.autocheck.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Die [Checklist] Datenklasse repräsentiert die Entität "Checklist" in der SQLite-Datenbank.
 *
 * @property id Die eindeutige ID der Checkliste, automatisch generiert.
 * @property light Der Zustand der Beleuchtung auf der Checkliste.
 * @property engine Der Zustand des Motors auf der Checkliste.
 * @property bumper Der Zustand des Stoßfängers auf der Checkliste.
 * @property sensor Der Zustand des Sensors auf der Checkliste.
 * @property exterior Der Zustand der äußeren Fahrzeugteile auf der Checkliste.
 * @property rims Der Zustand der Felgen auf der Checkliste.
 * @property brakes Der Zustand der Bremsen auf der Checkliste.
 * @property suspension Der Zustand der Aufhängung auf der Checkliste.
 * @property wheel Der Zustand des Rads auf der Checkliste.
 * @property sill Der Zustand der Schwelle auf der Checkliste.
 * @property mirror Der Zustand des Rückspiegels auf der Checkliste.
 * @property seat Der Zustand des Sitzes auf der Checkliste.
 * @property infotainment Der Zustand des Infotainmentsystems auf der Checkliste.
 * @property interior Der Zustand des Fahrzeuginnenraums auf der Checkliste.
 * @property exhaust Der Zustand des Auspuffs auf der Checkliste.
 */
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

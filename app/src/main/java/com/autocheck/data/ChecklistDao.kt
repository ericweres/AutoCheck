package com.autocheck.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

/**
 * Das [ChecklistDao] Interface definiert Datenbankzugriffsmethoden für die Entität "Checklist".
 */
@Dao
interface ChecklistDao {

    /**
     * Fügt eine neue Checkliste in die Datenbank ein oder aktualisiert sie, falls bereits vorhanden.
     *
     * @param checklist Die einzufügende oder zu aktualisierende Checkliste.
     * @return Die ID der eingefügten oder aktualisierten Checkliste.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(checklist: Checklist): Long

    /**
     * Ruft eine Checkliste anhand ihrer ID aus der Datenbank ab.
     *
     * @param id Die ID der abzurufenden Checkliste.
     * @return Die abgerufene Checkliste.
     */
    @Query("SELECT * FROM Checklist WHERE id = :id")
    suspend fun getById(id: Int): Checklist

    @Query("SELECT * FROM Checklist WHERE id IN (:ids)")
    suspend fun getByIds(ids: List<Int>): List<Checklist>

    /**
     * Aktualisiert eine vorhandene Checkliste in der Datenbank.
     *
     * @param checklist Die zu aktualisierende Checkliste.
     */
    @Update
    suspend fun update(checklist: Checklist)

    /**
     * Löscht eine Checkliste aus der Datenbank.
     *
     * @param checklist Die zu löschende Checkliste.
     */
    @Delete
    suspend fun delete(checklist: Checklist)
}
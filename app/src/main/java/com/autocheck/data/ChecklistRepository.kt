package com.autocheck.data

import javax.inject.Inject

/**
 * Die [ChecklistRepository] Klasse ist verantwortlich für die Datenbankoperationen bezüglich Checklisten.
 * Sie wird über Dependency Injection mit einem [ChecklistDao] instanziiert.
 */
class ChecklistRepository @Inject constructor(private val checklistDao: ChecklistDao) {

    /**
     * Ruft eine Checkliste anhand ihrer eindeutigen ID ab.
     *
     * @param id Die ID der abzurufenden Checkliste.
     * @return Die abgerufene Checkliste.
     */
    suspend fun getChecklistById(id: Int): Checklist {
        return checklistDao.getById(id)
    }

    /**
     * Ruft mehrere Checklisten anhand ihrer IDs ab.
     *
     * @param id Eine Liste von IDs der abzurufenden Checklisten.
     * @return Die abgerufenen Checklisten.
     */
    suspend fun getChecklistByIds(id: List<Int>): List<Checklist> {
        return checklistDao.getByIds(id)
    }

    /**
     * Fügt eine neue Checkliste in die Datenbank ein oder aktualisiert eine vorhandene.
     *
     * @param checklist Die einzufügende oder zu aktualisierende Checkliste.
     * @return Die ID der eingefügten oder aktualisierten Checkliste.
     */
    suspend fun insertOrUpdateChecklist(checklist: Checklist): Long {
        return checklistDao.insert(checklist)
    }

    /**
     * Löscht eine Checkliste aus der Datenbank.
     *
     * @param checklist Die zu löschende Checkliste.
     * TODO: Implementierung diese Funktion folgt später
     */
    @Suppress("UNUSED")
    suspend fun deleteChecklist(checklist: Checklist) {
        checklistDao.delete(checklist)
    }
}

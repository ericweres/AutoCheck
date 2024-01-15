package com.autocheck.data

import javax.inject.Inject

class ChecklistRepository @Inject constructor(private val checklistDao: ChecklistDao) {

    suspend fun getChecklistById(id: Int): Checklist {
        return checklistDao.getById(id)
     }

    suspend fun getChecklistByIds(id: List<Int>): List<Checklist> {
        return checklistDao.getByIds(id)
    }

    suspend fun insertOrUpdateChecklist(checklist: Checklist): Long {
        return checklistDao.insert(checklist)
    }

    suspend fun deleteChecklist(checklist: Checklist) {
        checklistDao.delete(checklist)
    }
}
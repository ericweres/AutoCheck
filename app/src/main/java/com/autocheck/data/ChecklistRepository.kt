package com.autocheck.data

import javax.inject.Inject

class ChecklistRepository @Inject constructor(private val checklistDao: ChecklistDao) {

    suspend fun getChecklistByCarId(carId: Int): Checklist {
        return checklistDao.getByCarId(carId)
     }

    suspend fun insertOrUpdateChecklist(checklist: Checklist) {
        checklistDao.insert(checklist)
    }

    suspend fun deleteChecklist(checklist: Checklist) {
        checklistDao.delete(checklist)
    }
}
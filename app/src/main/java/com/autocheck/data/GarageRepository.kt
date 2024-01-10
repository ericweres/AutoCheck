package com.autocheck.data

import javax.inject.Inject

class GarageRepository @Inject constructor(private val garageDao: GarageDao) {

    suspend fun insertGarage(garage: Garage) {
        garageDao.insertGarage(garage)
    }

    suspend fun updateGarage(garage: Garage) {
        garageDao.updateGarage(garage)
    }

    suspend fun getGarageByUserId(userId: Int): List<Garage> {
        return garageDao.getGarageByUserId(userId)
    }

    suspend fun removeFromGarage(userId: Int, carId: Int) {
        garageDao.removeFromGarage(userId, carId)
    }
}

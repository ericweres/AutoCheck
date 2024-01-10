package com.autocheck.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CarRepository @Inject constructor(private val carDao: CarDao) {

    fun getAllCars(): Flow<List<Car>> {
        return carDao.getAllCars()
    }

    suspend fun getCarByName(name: String): Flow<List<Car>> {
        return carDao.getCarByName(name)
    }
}

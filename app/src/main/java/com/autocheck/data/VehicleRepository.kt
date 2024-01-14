package com.autocheck.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VehicleRepository @Inject constructor(private val vehicleDao: VehicleDao) {
    suspend fun addVehicle(vehicle: Vehicle) {
        vehicleDao.insertVehicle(vehicle)
    }

    fun getAllCars(): Flow<List<Vehicle>> {
        return vehicleDao.getAllVehicles()
    }

    suspend fun getCarByName(name: String): Flow<List<Vehicle>> {
        return vehicleDao.getCarByName(name)
    }

    suspend fun getVehiclesByIds(ids: List<Int>): List<Vehicle> {
        return vehicleDao.getVehiclesByIds(ids)
    }

    fun getCarById(id: Int): Flow<Vehicle> {
        return vehicleDao.getCarById(id)
    }
}

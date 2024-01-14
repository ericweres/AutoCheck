package com.autocheck.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VehicleRepository @Inject constructor(private val vehicleDao: VehicleDao) {
    suspend fun addVehicle(vehicle: Vehicle) {
        vehicleDao.insertVehicle(vehicle)
    }

    suspend fun getAllVehicles(): List<Vehicle> {
        return vehicleDao.getAllVehicles()
    }

    suspend fun getVehiclesByName(name: String): List<Vehicle> {
        return vehicleDao.getVehiclesByName(name)
    }

    suspend fun getVehiclesByIds(ids: List<Int>): List<Vehicle> {
        return vehicleDao.getVehiclesByIds(ids)
    }

    suspend fun getVehicleById(id: Int): Vehicle? {
        return vehicleDao.getVehicleById(id)
    }
}

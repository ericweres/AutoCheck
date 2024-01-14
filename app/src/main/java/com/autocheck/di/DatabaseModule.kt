package com.autocheck.di

import android.content.Context
import androidx.room.Room
import com.autocheck.data.AppDatabase
import com.autocheck.data.VehicleDao
import com.autocheck.data.ChecklistDao
import com.autocheck.data.GarageDao
import com.autocheck.data.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "autocheck.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    fun provideChecklistDao(appDatabase: AppDatabase): ChecklistDao {
        return appDatabase.checklistDao()
    }

    @Provides
    fun provideGarageDao(appDatabase: AppDatabase): GarageDao {
        return appDatabase.garageDao()
    }

    @Provides
    fun provideVehicleDao(appDatabase: AppDatabase): VehicleDao {
        return appDatabase.vehicleDao()
    }
}
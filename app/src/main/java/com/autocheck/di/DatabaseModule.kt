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

/**
 * Das [DatabaseModule] Dagger Hilt (Dependency Injection (DI)-Framework für Android)
 * Modul bietet Abhängigkeiten für die Datenbankzugriffskomponenten.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /**
     * Stellt eine Instanz der [AppDatabase] bereit.
     *
     * @param appContext Der Anwendungskontext.
     * @return Die Datenbankinstanz.
     */
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

    /**
     * Stellt eine Instanz der [UserDao] bereit.
     *
     * @param appDatabase Die Datenbankinstanz.
     * @return Die [UserDao] Instanz.
     */
    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    /**
     * Stellt eine Instanz der [ChecklistDao] bereit.
     *
     * @param appDatabase Die Datenbankinstanz.
     * @return Die [ChecklistDao] Instanz.
     */
    @Provides
    fun provideChecklistDao(appDatabase: AppDatabase): ChecklistDao {
        return appDatabase.checklistDao()
    }

    /**
     * Stellt eine Instanz der [GarageDao] bereit.
     *
     * @param appDatabase Die Datenbankinstanz.
     * @return Die [GarageDao] Instanz.
     */
    @Provides
    fun provideGarageDao(appDatabase: AppDatabase): GarageDao {
        return appDatabase.garageDao()
    }

    /**
     * Stellt eine Instanz der [VehicleDao] bereit.
     *
     * @param appDatabase Die Datenbankinstanz.
     * @return Die [VehicleDao] Instanz.
     */
    @Provides
    fun provideVehicleDao(appDatabase: AppDatabase): VehicleDao {
        return appDatabase.vehicleDao()
    }
}

package com.autocheck.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Die [UserDao] Schnittstelle definiert die Datenbankzugriffsmethoden für die "User"-Entität.
 */
@Dao
interface UserDao {

    /**
     * Ruft alle Benutzer aus der Datenbank ab.
     *
     * @return Eine [Flow]-Instanz, die eine Liste von Benutzern repräsentiert.
     */
    @Query("SELECT * FROM user")
    fun getAllUsers(): Flow<List<User>>

    /**
     * Ruft Benutzer anhand ihrer IDs aus der Datenbank ab.
     *
     * @param userIds Ein Array von Benutzer-IDs.
     * @return Eine [Flow]-Instanz, die eine Liste von Benutzern repräsentiert.
     */
    @Query("SELECT * FROM user WHERE userId IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): Flow<List<User>>

    /**
     * Sucht nach einem Benutzer anhand seiner E-Mail-Adresse.
     *
     * @param email Die E-Mail-Adresse des zu suchenden Benutzers.
     * @return Der gefundene Benutzer oder null, wenn kein Benutzer mit der angegebenen E-Mail gefunden wurde.
     */
    @Query("SELECT * FROM user WHERE email LIKE :email LIMIT 1")
    suspend fun findByEmail(email: String): User?

    /**
     * Fügt einen neuen Benutzer in die Datenbank ein.
     *
     * @param user Der einzufügende Benutzer.
     */
    @Insert
    suspend fun insert(user: User)

    /**
     * Löscht einen Benutzer aus der Datenbank.
     *
     * @param user Der zu löschende Benutzer.
     */
    @Delete
    suspend fun delete(user: User)
}

package com.autocheck.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Die [UserRepository] Klasse ist verantwortlich für die Koordination von Datenbankzugriffen bezüglich Benutzerdaten.
 * Sie wird über Dependency Injection mit einem [UserDao] instanziiert.
 */
class UserRepository @Inject constructor(private val userDao: UserDao) {

    /**
     * Fügt einen neuen Benutzer in die Datenbank ein.
     *
     * @param username Der Benutzername des neuen Benutzers.
     * @param email Die E-Mail-Adresse des neuen Benutzers.
     * @param password Das Passwort des neuen Benutzers.
     */
    suspend fun addUser(username: String, email: String, password: String) {
        val newUser = User(username = username, email = email, password = password)
        userDao.insert(newUser)
    }

    /**
     * Ruft einen Benutzer anhand seiner E-Mail-Adresse ab.
     *
     * @param email Die E-Mail-Adresse des zu suchenden Benutzers.
     * @return Der gefundene Benutzer oder null, wenn kein Benutzer mit der angegebenen E-Mail gefunden wurde.
     */
    suspend fun getUserByEmail(email: String): User? {
        return userDao.findByEmail(email)
    }

    /**
     * Ruft alle Benutzer aus der Datenbank ab und liefert sie als [Flow].
     *
     * @return Eine [Flow]-Instanz, die eine Liste von Benutzern repräsentiert.
     * TODO: Implementierung diese Funktion folgt später
     */
    @Suppress("UNUSED")
    fun getAllUsers(): Flow<List<User>> = userDao.getAllUsers()
}

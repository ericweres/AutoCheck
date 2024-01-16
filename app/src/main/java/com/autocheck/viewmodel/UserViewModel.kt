package com.autocheck.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.autocheck.data.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.mindrot.jbcrypt.BCrypt
import javax.inject.Inject

/**
 * ViewModel für die Benutzerinteraktionen in der AutoCheck-App.
 *
 * Diese Klasse ist mit Hilt annotiert, um Abhängigkeiten über Hilt-Dagger zu verwalten.
 * Sie ist verantwortlich für die Verwaltung von Benutzerdaten und Interaktionen,
 * einschließlich Benutzerregistrierung und Anmeldung.
 *
 * @property repository Ein [UserRepository]-Objekt zur Datenverwaltung für Benutzer.
 */
@HiltViewModel
class UserViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {

    // MutableStateFlow für die Benutzer-ID
    private var _userId = MutableStateFlow(-1)
    val userId: StateFlow<Int> = _userId

    // MutableStateFlow für den Benutzernamen
    private var _username = MutableStateFlow("Nicht Angemeldet")
    val username: Flow<String> = _username

    /**
     * Funktion zur Registrierung eines neuen Benutzers.
     *
     * @param username Der Benutzername des neuen Benutzers.
     * @param email Die E-Mail-Adresse des neuen Benutzers.
     * @param password Das Passwort des neuen Benutzers.
     */
    fun addUser(username: String, email: String, password: String) {
        viewModelScope.launch {
            // Hashen des Passworts mit BCrypt und Hinzufügen des Benutzers zur Datenbank
            val hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt())
            repository.addUser(username, email, hashedPassword)
        }
    }

    /**
     * Funktion zum Anmelden eines Benutzers.
     *
     * @param email Die E-Mail-Adresse des Benutzers.
     * @param password Das Passwort des Benutzers.
     * @param onResult Callback, das das Ergebnis der Anmeldung als Boolean empfängt.
     */
    fun loginUser(email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            // Abrufen des Benutzers anhand der E-Mail-Adresse
            val user = repository.getUserByEmail(email)
            user?.let {
                // Überprüfen, ob das eingegebene Passwort mit dem gespeicherten Passwort übereinstimmt
                val doesPasswordMatch = BCrypt.checkpw(password, user.password)
                if (doesPasswordMatch) {
                    // Bei Übereinstimmung: Aktualisieren der Benutzer-ID und des Benutzernamens
                    _userId.value = user.userId
                    _username.value = user.username
                }
                // Callback mit dem Ergebnis der Anmeldung
                onResult(doesPasswordMatch)
            } ?: onResult(false)
        }
    }
}

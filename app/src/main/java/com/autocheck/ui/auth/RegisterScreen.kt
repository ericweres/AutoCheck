package com.autocheck.ui.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.autocheck.viewmodel.UserViewModel

/**
 * [RegisterScreen] ist das Composable für die Anzeige des Registrierungs-Bildschirms.
 *
 * @param navController Der [NavHostController], der für die Navigation verantwortlich ist.
 * @param viewModel Das [UserViewModel], das für die Benutzerverwaltung zuständig ist.
 */
@Composable
fun RegisterScreen(navController: NavHostController, viewModel: UserViewModel) {
    // Zustandsvariablen für Benutzereingaben und Sichtbarkeit des Passworts.
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }

    // Fehlermeldungen für Benutzereingaben.
    var usernameError by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }

    // Verfolgung, ob Eingabefelder berührt wurden.
    var usernameTouched by remember { mutableStateOf(false) }
    var emailTouched by remember { mutableStateOf(false) }
    var passwordTouched by remember { mutableStateOf(false)   }

    // Abgeleiteter Zustand für die Formularvalidierung.
    val isFormValid by remember {
        derivedStateOf {
            emailError.isEmpty() && usernameError.isEmpty() && passwordError.isEmpty()
        }
    }

    // Validierung der Benutzereingaben für den Benutzernamen.
    fun validateUsername(): Boolean {
        return if (username.isBlank()) {
            usernameError = "Bitte Username eingeben"
            false
        } else {
            usernameError = ""
            true
        }
    }

    // Validierung der Benutzereingaben für das Passwort.
    fun validatePassword() {
        passwordError = when {
            password.isBlank() || confirmPassword.isBlank() -> {
                "Bitte Passwort eingeben"
            }
            password != confirmPassword -> {
                "Passwörter stimmen nicht überein"
            }
            else -> {
                ""
            }
        }
    }

    // Validierung der Benutzereingaben für die E-Mail.
    fun validateEmail(): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+" // Simple email regex pattern
        val isValidEmail = email.matches(Regex(emailPattern))

        emailError = if (email.isBlank()) {
            "Bitte Email eingeben"
        } else if (!isValidEmail) {
            "Bitte gültige Email eingeben"
        } else {
            ""
        }
        return isValidEmail && email.isNotEmpty()
    }

    // UI-Komponenten für die Registrierungsansicht.
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "Neues Konto erstellen",
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Wir helfen Ihnen, sich beim Kauf eines neuen Fahrzeugs sicher zu fühlen",
            fontSize = 16.sp,
            color = Color.Gray,

        )

        Spacer(modifier = Modifier.height(50.dp))
        // Eingabefeld für den Benutzernamen.
        OutlinedTextField(
            value = username,
            onValueChange = {
                username = it
                usernameTouched = true
                validateUsername()
            },
            label = { Text("name") },

            isError = usernameTouched && usernameError.isNotEmpty(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        )
        // Anzeige der Fehlermeldung, wenn vorhanden.
        if (usernameTouched && usernameError.isNotEmpty()) {
            Text(usernameError, color = MaterialTheme.colorScheme.error)
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Eingabefeld für die E-Mail.
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailTouched = true
                validateEmail()
            },
            label = { Text("email") },
            isError = emailTouched && emailError.isNotEmpty(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        )
        // Anzeige der Fehlermeldung, wenn vorhanden.
        if (emailTouched && emailError.isNotEmpty()) {
            Text(emailError, color = MaterialTheme.colorScheme.error)
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Eingabefeld für das Passwort.
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                passwordTouched = true
                validatePassword()

            },
            label = { Text("password") },
            singleLine = true,
            isError = passwordTouched && passwordError.isNotEmpty(),
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            trailingIcon = {
                val image = if (passwordVisibility)
                    Icons.Filled.Visibility
                else
                    Icons.Filled.VisibilityOff

                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(image, "Toggle password visibility")
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        // Eingabefeld zur Bestätigung des Passworts.
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                validatePassword()
                            },
            label = { Text("confirm password") },
            singleLine = true,
            isError = passwordTouched && passwordError.isNotEmpty(),
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            trailingIcon = {
                val image = if (passwordVisibility)
                    Icons.Filled.Visibility
                else
                    Icons.Filled.VisibilityOff

                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(image, "Toggle password visibility")
                }
            },
            modifier = Modifier
                .onFocusChanged { focusState ->
                    if (!focusState.isFocused) {
                        validatePassword()
                    }
                }
        )
        // Anzeige der Fehlermeldung.
        if (passwordTouched && passwordError.isNotEmpty()) {
            Text(passwordError, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(100.dp))
        // Button zum Ausführen der Registrierung.
        Button(
            onClick = {
                        viewModel.addUser(username, email, password)
                        navController.navigate("login")
                      },
            modifier = Modifier
                .width(302.dp)
                .height(62.dp),
            enabled = isFormValid,
        ) {
            Text(
                text = "Registrieren",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight(700),
                )
            )
        }

        // Button zum Wechseln zur Anmeldeseite.
        TextButton(onClick = { navController.navigate("login") }) {
            Text(
                text = "Bereits ein Konto? Zur Anmeldung",
                color = Color.Blue
            )
        }
    }
}
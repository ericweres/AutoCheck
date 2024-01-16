package com.autocheck.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.autocheck.ui.theme.AutoCheckTheme
import dagger.hilt.android.AndroidEntryPoint

// Mit AndroidEntryPoint wird die Hilt-Integration für die Android-Anwendung aktiviert.
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Setze den Inhalt der Aktivität auf das AutoCheckTheme
        setContent {
            AutoCheckTheme {
                // Rufe die Hauptkomponente AutoCheckApp auf
                AutoCheckApp()
            }
        }
    }
}

/**
 * Die AutoCheckApp-Funktion definiert die Hauptkomponente der Anwendung.
 * Sie erstellt einen NavController und ruft die Hauptnavigation AutoCheckNavHost auf.
 * navController Ein NavController, der die Navigation in der Anwendung steuert.
 */
@Composable
fun AutoCheckApp() {
    // Erstelle einen NavController mithilfe von rememberNavController, um den Navigationszustand zu erhalten.
    val navController = rememberNavController()
    // Rufe die Hauptnavigation AutoCheckNavHost auf und übergib den erstellten NavController.
    AutoCheckNavHost(
        navController = navController,
    )
}

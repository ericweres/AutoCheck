package com.autocheck.ui

import com.autocheck.ui.home.HomeScreen
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.autocheck.ui.nav.BottomNavigationBar
import com.autocheck.ui.nav.TopNavigationBar
import com.autocheck.ui.auth.GetStarted
import com.autocheck.ui.auth.LoginScreen
import com.autocheck.ui.auth.RegisterScreen
import com.autocheck.ui.home.GarageScreen
import com.autocheck.ui.home.SearchScreen
import com.autocheck.ui.home.Werkstaetten
import com.autocheck.ui.illustrations.CheckList
import com.autocheck.ui.illustrations.IllustBike
import com.autocheck.ui.illustrations.IllustKombi
import com.autocheck.viewmodel.UserViewModel

/**
 * Composable-Funktion für den Navigationsgraphen der AutoCheck-App.
 *
 * Diese Funktion definiert den Navigationsgraphen der App, der verschiedene Bildschirme
 * mit ihren entsprechenden Composables verbindet. Jeder Bildschirm ist in einem [Scaffold]
 * mit Top- und Bottom-Navigation eingebettet.
 *
 * @param navController [NavHostController] für die Navigation zwischen den Bildschirmen.
 * @param modifier Ein optionaler Modifier zur Steuerung der Layout-Eigenschaften des Navigationsgraphen.
 */
@Composable
fun AutoCheckNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    // HiltViewModel wird für die Abhängigkeitsinjektion verwendet, um den ViewModel zu erhalten
    val viewModel: UserViewModel = hiltViewModel()

    // Navigations-Host, der die verschiedenen Composables für die App verbindet
    NavHost(
        navController = navController,
        startDestination = "search", // Standardstartziel der App
        modifier = modifier,
    ) {
        // Composable für den Login-Bildschirm
        composable("login") {
            LoginScreen(navController, viewModel)
        }

        // Composable für den "Get Started"-Bildschirm
        composable("getStarted") {
            GetStarted(navController)
        }

        // Composable für den Registrierungs-Bildschirm
        composable("register") {
            RegisterScreen(navController, viewModel)
        }

        // Composable für den Hauptbildschirm ("home") mit Scaffold für Top- und Bottom-Navigation
        composable("home") {
            Scaffold(
                bottomBar = { BottomNavigationBar(modifier = Modifier, navController) },
                topBar = { TopNavigationBar(modifier = Modifier, navController, "Startseite") },
            ) { innerPadding ->
                HomeScreen(
                    modifier = Modifier.padding(innerPadding),
                    viewModel
                )
            }
        }

        // Weitere Composables für Werkstätten, Kombi, Motorrad, Checkliste, Suche und Garage
        // Jedes Composable ist in einem Scaffold mit Top- und Bottom-Navigation eingebettet
        composable("werkstaetten") {
            Scaffold(
                bottomBar = { BottomNavigationBar(modifier = Modifier, navController) },
                topBar = { TopNavigationBar(modifier = Modifier, navController, "Werkstätten") },
            ) { innerPadding ->
                Werkstaetten(
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }

        // Composable für "kombi/{vehicleId}" mit Übergabe der Fahrzeug-ID
        composable("kombi/{vehicleId}") { backStackEntry ->
            val vehicleId = backStackEntry.arguments?.getString("vehicleId")?.toIntOrNull()
            Scaffold(
                bottomBar = { BottomNavigationBar(modifier = Modifier, navController) },
                topBar = { TopNavigationBar(modifier = Modifier, navController, "Kombi") },
            ) { innerPadding ->
                IllustKombi(
                    modifier = Modifier.padding(innerPadding),
                    navController,
                    vehicleId = vehicleId
                )
            }
        }

        // Composable für "bike/{vehicleId}" mit Übergabe der Fahrzeug-ID
        composable("bike/{vehicleId}") { backStackEntry ->
            val vehicleId = backStackEntry.arguments?.getString("vehicleId")?.toIntOrNull()
            Scaffold(
                bottomBar = { BottomNavigationBar(modifier = Modifier, navController) },
                topBar = { TopNavigationBar(modifier = Modifier, navController, "Motorrad") },
            ) { innerPadding ->
                IllustBike(
                    modifier = Modifier.padding(innerPadding),
                    navController,
                    vehicleId = vehicleId
                )
            }
        }

        // Composable für "checkList/{vehicleId}" mit Übergabe der Fahrzeug-ID
        composable("checkList/{vehicleId}") { backStackEntry ->
            val vehicleId = backStackEntry.arguments?.getString("vehicleId")?.toIntOrNull()
            Scaffold(
                bottomBar = { BottomNavigationBar(modifier = Modifier, navController) },
                topBar = { TopNavigationBar(modifier = Modifier, navController, "Checkliste") },
            ) { innerPadding ->
                CheckList(
                    modifier = Modifier.padding(innerPadding),
                    navController,
                    vehicleId = vehicleId,
                    viewModel
                )
            }
        }

        // Weitere Composables für "search" und "garage"
        // Jedes Composable ist in einem Scaffold mit Top- und Bottom-Navigation eingebettet
        composable("search") {
            Scaffold(
                bottomBar = { BottomNavigationBar(modifier = Modifier, navController) },
                topBar = { TopNavigationBar(modifier = Modifier, navController, "Suche") },
            ) { innerPadding ->
                SearchScreen(modifier = Modifier.padding(innerPadding), navController)
            }
        }

        composable("garage") {
            Scaffold(
                bottomBar = { BottomNavigationBar(modifier = Modifier, navController) },
                topBar = { TopNavigationBar(modifier = Modifier, navController, "Meine Garage") },
            ) { innerPadding ->
                GarageScreen(
                    modifier = Modifier.padding(innerPadding),
                    viewModel
                )
            }
        }
    }
}

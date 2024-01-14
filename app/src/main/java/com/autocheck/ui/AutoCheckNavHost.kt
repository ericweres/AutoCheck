package com.autocheck.ui

import HomeScreen
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import com.autocheck.ui.nav.Search

@Composable
fun AutoCheckNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    ) {
    NavHost(
        navController = navController,
        startDestination = "search",
        modifier = modifier,
    ) {
        composable( "login") {
            LoginScreen(navController)
        }
        composable( "getStarted") {
            GetStarted(navController)
        }
        composable( "register") {
            RegisterScreen(navController)
        }
       composable("home") {
           Scaffold(
               bottomBar = { BottomNavigationBar(modifier = Modifier,
                   navController) },
               topBar = { TopNavigationBar(modifier = Modifier,
                   navController,"Startseite") },
           ) { innerPadding ->
                HomeScreen(
                   modifier = Modifier.padding(innerPadding)
               )
           }
       }
        composable("werkstaetten") {
            Scaffold(
                bottomBar = { BottomNavigationBar(modifier = Modifier,
                    navController) },
                topBar = { TopNavigationBar(modifier = Modifier,
                    navController,"Werkstätten") },
            ) { innerPadding ->
                Werkstaetten(
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }

        composable( "kombi/{vehicleId}") {
                backStackEntry ->
            val vehicleId = backStackEntry.arguments?.getString("vehicleId")?.toIntOrNull()
            Scaffold(
                bottomBar = { BottomNavigationBar(modifier = Modifier,
                    navController) },
                topBar = { TopNavigationBar(modifier = Modifier,
                    navController,"Kombi") },
            ) { innerPadding ->
                IllustKombi(
                    modifier = Modifier.padding(innerPadding),
                    navController,
                    vehicleId = vehicleId
                )
            }
        }

        composable("bike/{vehicleId}") {
                backStackEntry ->
            val vehicleId = backStackEntry.arguments?.getString("vehicleId")?.toIntOrNull()
            Scaffold(
                bottomBar = { BottomNavigationBar(modifier = Modifier,
                    navController) },
                topBar = { TopNavigationBar(modifier = Modifier,
                    navController,"Motorrad") },
            ) { innerPadding ->
                IllustBike(
                    modifier = Modifier.padding(innerPadding),
                    navController,
                    vehicleId = vehicleId
                )
            }
        }

        composable( route = "checkList/{vehicleId}") {
                backStackEntry ->
            val vehicleId = backStackEntry.arguments?.getString("vehicleId")?.toIntOrNull()
            Scaffold(
                bottomBar = { BottomNavigationBar(modifier = Modifier,
                    navController) },
                topBar = { TopNavigationBar(modifier = Modifier,
                    navController,"Checkliste") },
            ) { innerPadding ->
                CheckList(
                    modifier = Modifier.padding(innerPadding),
                    navController,
                    vehicleId = vehicleId
                )
            }
        }


        composable( route = "search") {
            Scaffold(
                bottomBar = { BottomNavigationBar(modifier = Modifier,
                    navController) },
                topBar = { TopNavigationBar(modifier = Modifier,
                    navController,"Suche") },
            ) { innerPadding ->
                SearchScreen(modifier = Modifier.padding(innerPadding), navController)
            }
        }


        composable( route = "garage") {
            Scaffold(
                bottomBar = { BottomNavigationBar(modifier = Modifier,
                    navController) },
                topBar = { TopNavigationBar(modifier = Modifier,
                    navController,"Meine Garage") },
            ) { innerPadding ->
                GarageScreen(
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }

    }
}
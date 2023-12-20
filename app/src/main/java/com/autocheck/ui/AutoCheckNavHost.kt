package com.autocheck.ui

import HomeScreen
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.autocheck.nav.BottomNavigationBar
import com.autocheck.nav.TopNavigationBar
import com.autocheck.ui.auth.GetStarted
import com.autocheck.ui.auth.LoginScreen
import com.autocheck.ui.auth.RegisterScreen
@Composable
fun AutoCheckNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    ) {
    NavHost(
        navController = navController,
        startDestination = "getStarted",
        modifier = modifier,
    ) {
        composable( route = "login") {
            LoginScreen(navController)
        }
        composable( route = "getStarted") {
            GetStarted(navController)
        }
        composable( route = "register") {
            RegisterScreen(navController)
        }
       composable( route = "home") {
           Scaffold(
               bottomBar = { BottomNavigationBar() },
               topBar = { TopNavigationBar() },
           ) { innerPadding ->
                HomeScreen(
                   modifier = Modifier.padding(innerPadding)
               )
           }
       }
    }
}
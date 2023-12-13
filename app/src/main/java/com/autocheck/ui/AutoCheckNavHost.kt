package com.autocheck.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.autocheck.ui.auth.GetStarted
import com.autocheck.ui.auth.LoginScreen

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
            LoginScreen()
        }
        composable( route = "getStarted") {
            GetStarted(navController)
        }
//        composable( route = "register") {
//            RegisterScreen()
//        }
    }
}
package com.autocheck.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHost
import androidx.navigation.compose.rememberNavController
import com.autocheck.nav.BottomNavigationBar
import com.autocheck.nav.TopNavigationBar
import com.autocheck.ui.auth.GetStarted
import com.autocheck.ui.auth.LoginScreen
import com.autocheck.ui.theme.AutoCheckTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AutoCheckTheme {
                AutoCheckApp()
            }
        }
    }
}

@Composable
fun AutoCheckApp() {
    val navController = rememberNavController()
    AutoCheckNavHost(
        navController = navController,
    )
}



@Preview(showBackground = true)
@Composable
fun AppPreview() {
    AutoCheckTheme {
        AutoCheckApp()
    }
}
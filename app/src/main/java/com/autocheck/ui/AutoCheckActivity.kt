package com.autocheck.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.autocheck.ui.theme.AutoCheckTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppPreview() {
    AutoCheckTheme {
        AutoCheckApp()
    }
}
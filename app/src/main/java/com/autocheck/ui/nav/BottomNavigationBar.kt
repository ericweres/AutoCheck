package com.autocheck.ui.nav

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.autocheck.R
import com.autocheck.ui.theme.AutoCheckTheme

@Composable
fun BottomNavigationBar(modifier: Modifier = Modifier, navController: NavHostController) {
    // Get screen height
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    // Calculate 10% of screen height
    val navBarHeight = screenHeight * 0.1f

    // Apply height and fillMaxWidth to the modifier
    val updatedModifier = modifier
        .height(navBarHeight)
        .fillMaxWidth()

    NavigationBar(
        containerColor = Color(0xFF5BDABB),
        modifier = updatedModifier
    )
    {
        var selectedW by rememberSaveable { mutableStateOf(false) }
        var selectedG by rememberSaveable { mutableStateOf(false) }

        DisposableEffect(navController) {
            navController.addOnDestinationChangedListener { _, destination, _ ->
                selectedW = destination.route == "werkstaetten"
                selectedG = destination.route == "garage"
            }

            onDispose {
                // Aufräumoperationen, wenn der DisposableEffect beendet wird
            }
        }

        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_werkstatt),
                    contentDescription = null,
                    tint = if (selectedW) Color.Blue else Color.Black
                )
            },
            label = {
                Text(
                    text = "Werkstätte",
                    color = if (selectedW) Color.Blue else Color.Black
                )
            },
            selected = selectedW,
            onClick = {
                navController.navigate("werkstaetten")
            }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_camera),
                    contentDescription = null,
                    tint = if (false) Color.White else Color.Black
                )
            },
            label = {
                Text(
                    text = "Foto machen",
                    color = if (false) Color.White else Color.Black
                )
            },
            selected = false,
            onClick = {                navController.navigate("login")
            }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_garage),
                    contentDescription = null,
                    tint = if (selectedG) Color.Blue else Color.Black
                )
            },
            label = {
                Text(
                    text = "Meine Garage",
                    color = if (selectedG) Color.Blue else Color.Black
                )
            },
            selected = selectedG,
            onClick = { navController.navigate("garage") }
        )
    }
}




@Preview
@Composable
fun BottomNavigationBarPreview() {
    // Rufe die BottomNavigationBar-Funktion auf
    BottomNavigationBar(navController = rememberNavController())

}

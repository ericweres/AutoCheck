package com.autocheck.ui.nav

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.autocheck.R
import com.autocheck.ui.theme.AutoCheckTheme

@Preview
@Composable
fun BottomNavigationBarPreview() {
    AutoCheckTheme {
        BottomNavigationBar()
    }
}

@Composable
fun BottomNavigationBar(modifier: Modifier = Modifier) {
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
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_werkstatt),
                    contentDescription = null,
                )
            },
            label = {
                Text("Werkstätte")
            },
            selected = false,
            onClick = {}
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_camera),
                    contentDescription = null
                )
            },
            label = {
                Text("Foto machen")
            },
            selected = false,
            onClick = {}
        )

        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_garage),
                    contentDescription = null
                )
            },
            label = {
                Text("Meine Garage")
            },
            selected = false,
            onClick = {}
        )
    }
}
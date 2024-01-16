package com.autocheck.ui.nav

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.autocheck.R

/**
 * Composable-Funktion für die obere Navigationsleiste.
 *
 * Diese Funktion erstellt eine benutzerdefinierte Navigationsleiste mit einem Titel, Profilbild,
 * Such- und Home-Icons sowie Navigationsaktionen.
 *
 * @param modifier Ein optionaler Modifier zur Steuerung der Layout-Eigenschaften der Navigationsleiste.
 * @param navController [NavHostController] für die Navigation zwischen den Bildschirmen.
 * @param title Der Titel, der in der Navigationsleiste angezeigt wird.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavigationBar(
    @Suppress("UNUSED_PARAMETER")modifier: Modifier = Modifier,
    navController: NavHostController,
    title: String
) {
    // Obere Navigationsleiste mit zentriertem Titel, Profilbild und Aktionen erstellen
    CenterAlignedTopAppBar(
        title = { Text(title) }, // Titel setzen
        navigationIcon = {
            // Profilbild als Navigations-Icon verwenden
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile",
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(40.dp)
                    .clip(CircleShape)
            )
        },
        actions = {
            // Such-Icon mit Navigationsaktion erstellen
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search",
                modifier = Modifier
                    .size(24.dp)
                    .clickable { navController.navigate("search") }
            )
            // Leerraum zwischen den Icons hinzufügen
            Spacer(Modifier.width(16.dp))
            // Home-Icon mit Navigationsaktion erstellen
            Icon(
                painter = painterResource(id = R.drawable.ic_home),
                contentDescription = "Home",
                modifier = Modifier
                    .size(24.dp)
                    .clickable { navController.navigate("home") }
            )
            // Leerraum zwischen den Icons hinzufügen
            Spacer(Modifier.width(16.dp))
        },
        // Farben für die Navigationsleiste festlegen
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xFF5BDABB), // Farbe an das Design anpassen
            titleContentColor = Color.Black,
            actionIconContentColor = Color.Black
        )
    )
}

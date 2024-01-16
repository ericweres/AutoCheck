package com.autocheck.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.autocheck.R
import com.autocheck.viewmodel.UserViewModel

/**
 * Composable-Funktion für die com.autocheck.ui.home.HomeScreen-Benutzeroberfläche.
 *
 * Diese Funktion erstellt die UI für die Startseite der Android-Anwendung mit Jetpack Compose.
 *
 * @param modifier Modifier zur Steuerung der Layout-Eigenschaften der UI-Elemente.
 * @param userViewModel Das ViewModel, das die Benutzerdaten enthält.
 */
@Composable
fun HomeScreen(@Suppress("UNUSED_PARAMETER")modifier: Modifier, userViewModel: UserViewModel) {
    // Verwendung von remember und mutableStateOf für den Switch-Zustand
    var checked by remember { mutableStateOf(false) }
    // Sammeln des Benutzernamens aus dem ViewModel
    val username by userViewModel.username.collectAsState("Nicht Angemeldet")

    // UI-Elemente werden in einer Spaltenanordnung platziert
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        // Begrüßungstext mit Benutzernamen
        Text(
            text = "Hallo $username, \n" +
                    "was würdest du gerne \n" +
                    "als nächstes tun?",
            fontSize = 25.sp,
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.height(50.dp))

        // Schalter für die Anzeige von "News"
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = "News",
                fontSize = 23.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.weight(1f))

            // Switch für die Anzeige von News
            Switch(
                checked = checked,
                onCheckedChange = {
                    checked = it
                }
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        // Bedingte Anzeige eines Bildes basierend auf dem Switch-Zustand
        if (!checked) {
            Image(
                painter = painterResource(id = R.drawable.homescreen_bild),
                contentDescription = "Homescreen Car",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.news),
                contentDescription = "News Platzhalter",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            )
        }
    }
}


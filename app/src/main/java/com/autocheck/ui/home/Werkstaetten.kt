package com.autocheck.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.autocheck.R
import com.autocheck.ui.theme.AutoCheckTheme


/**
 * Composable-Funktion für die Anzeige von Werkstätten.
 *
 * Diese Funktion erstellt die UI, um Werkstätten anzuzeigen.
 *
 * @param modifier Modifier zur Steuerung der Layout-Eigenschaften der UI-Elemente.
 */
@Suppress("UNUSED_PARAMETER")
@Composable
fun Werkstaetten(modifier: Modifier) {
    // LazyColumn für die Anordnung der UI-Elemente
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Erstes Element: Abstand und Bild "map"
        item {
            Spacer(modifier = Modifier.height(60.dp))
            Image(
                painter = painterResource(id = R.drawable.map),
                contentDescription = "map",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )
        }

        // Zweites Element: Abstand, Bild "werkstaetten" und zusätzlicher Abstand
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = R.drawable.werkstaetten),
                contentDescription = "werkstätte beispiel",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(550.dp)
            )
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

/**
 * Vorschau-Funktion für die Anzeige von Werkstätten.
 * Zeigt die UI in der Design-Vorschau an.
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppPreview() {
    AutoCheckTheme {
        Werkstaetten(modifier = Modifier)
    }
}

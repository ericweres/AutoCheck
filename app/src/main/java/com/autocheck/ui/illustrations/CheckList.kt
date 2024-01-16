package com.autocheck.ui.illustrations

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.autocheck.data.Checklist
import com.autocheck.viewmodel.ChecklistViewModel
import com.autocheck.viewmodel.GarageViewModel
import com.autocheck.viewmodel.UserViewModel
import com.autocheck.viewmodel.VehicleViewModel

/**
 * Composable-Funktion für die Anzeige der Checkliste.
 *
 * Diese Funktion erstellt die UI für die Checkliste eines Fahrzeugs.
 *
 * @param modifier Modifier zur Steuerung der Layout-Eigenschaften der UI-Elemente.
 * @param navController [NavHostController] für die Navigation zwischen Bildschirmen.
 * @param vehicleId Die ID des ausgewählten Fahrzeugs.
 * @param userViewModel [UserViewModel] für die Benutzerinformationen.
 */
@Composable
fun CheckList(modifier: Modifier, navController: NavHostController, vehicleId: Int?,userViewModel: UserViewModel
) {
    // Liste von Optionen für den Radio-Button (Gut, Mittel, Schlecht)
    val radioOptions = listOf("Gut", "Mittel", "Schlecht")
    // Liste von Fahrzeugteilen für die Checkliste
    val carParts = listOf(
        "Scheinwerfer",
        "Motor",
        "Stoßstange",
        "Sensoren",
        "Karosserie",
        "Reifen",
        "Felgen",
        "Bremsen",
        "Suspension",
        "Radlauf",
        "Fensterbrett",
        "Spiegel",
        "Sitze",
        "Infotainment",
        "Innenraum",
        "Auspuff"
    )

    // ViewModels für die Geschäftslogik
    val garageViewModel: GarageViewModel = hiltViewModel()
    val checklistViewModel: ChecklistViewModel = hiltViewModel()
    val vehicleViewModel: VehicleViewModel = hiltViewModel()
    // Benutzer-ID sammeln
    val userId by userViewModel.userId.collectAsState()

    // Aktuell ausgewähltes Fahrzeug und gespeicherte Checklisten-ID sammeln
    val selectedVehicle by vehicleViewModel.selectedVehicle.collectAsState()
    val savedChecklistId by checklistViewModel.savedChecklistId.collectAsState()

    // Zustand, um ausgewählte Optionen für jedes Fahrzeugteil zu speichern
    val selectedOptions = remember { mutableStateOf(mapOf<String, Int>()) }

    // Wenn eine Fahrzeug-ID Not null ist
    if (vehicleId != null) {
        // Fahrzeuginformationen vom ViewModel abrufen
        vehicleViewModel.fetchVehicleById(vehicleId)


        Column ( modifier = modifier
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = selectedVehicle.name,
                style = MaterialTheme.typography.headlineLarge
            )
            Button(onClick = {
                // Erstellen einer Checkliste basierend auf ausgewählten Optionen
                val checklist = Checklist(
                    light = selectedOptions.value["Scheinwerfer"],
                    engine = selectedOptions.value["Motor"],
                    bumper = selectedOptions.value["Stoßstange"],
                    sensor = selectedOptions.value["Sensoren"],
                    exterior = selectedOptions.value["Karosserie"],
                    tires = selectedOptions.value["Reifen"],
                    rims = selectedOptions.value["Felgen"],
                    brakes = selectedOptions.value["Bremsen"],
                    suspension = selectedOptions.value["Suspension"],
                    wheel = selectedOptions.value["Radlauf"],
                    sill = selectedOptions.value["Fensterbrett"],
                    mirror = selectedOptions.value["Spiegel"],
                    seat = selectedOptions.value["Sitze"],
                    infotainment = selectedOptions.value["Infotainment"],
                    interior = selectedOptions.value["Innenraum"],
                    exhaust = selectedOptions.value["Auspuff"],
                )
                // Speichern der Checkliste und Weiterleitung zur Garage
                checklistViewModel.saveChecklist(checklist)
                             },
                // Button ist nur aktiviert, wenn alle Optionen ausgewählt sind
                enabled = selectedOptions.value.size == carParts.size
            ) {
                Text("Parken",color = MaterialTheme.colorScheme.onPrimary)
            }
        }
            // Überwachen des Zustands der gespeicherten Checklisten-ID
            LaunchedEffect(savedChecklistId) {
                // Wenn eine Checklisten-ID vorhanden ist, Fahrzeug zur Garage hinzufügen und zur Garage navigieren
                if (savedChecklistId != -1) {
                    garageViewModel.addVehicleToGarage(userId, vehicleId, savedChecklistId)
                    navController.navigate("garage")
                }
            }
            // Liste von Fahrzeugteilen in einer LazyColumn anzeigen
        LazyColumn {
        items(carParts) { teil ->
            // Hintergrundfargrundfarbe basierend auf der ausgewählten Option
            val backgroundColor = when (selectedOptions.value[teil] ?: 0) {
                1 -> Color.Green.copy(alpha = 0.2f)
                2 -> Color(0xFFFFA500).copy(alpha = 0.2f)
                3 -> Color.Red.copy(alpha = 0.2f)
                else -> Color.Transparent
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = backgroundColor)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                //.border(1.dp, Color.Gray, shape = RoundedCornerShape(0.dp)),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                radioOptions.forEachIndexed { index, _ ->
                    val isSelected = selectedOptions.value[teil] == index + 1
                    RadioButton(
                        selected = isSelected,
                        onClick = {
                            // Ausgewählte Option für das Fahrzeugteil aktualisieren
                            selectedOptions.value += (teil to index + 1)

                            // Log-Nachricht für die Aktualisierung der ausgewählten Optionen
                            Log.d("MyScreen", "Selected options updated: ${selectedOptions.value}")

                        }
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontSize = 24.sp,
                            )
                        ) {
                            append("$teil: ")
                        }
                        val innerSelectedOption = selectedOptions.value[teil]
                        val color = when (innerSelectedOption) {
                            1 -> Color.Green
                            2 -> Color(0xFFFFA500)
                            3 -> Color.Red
                            else -> Color.Gray
                        }
                        val zustand = when (innerSelectedOption) {
                            1 -> "Gut"
                            2 -> "Mittel"
                            3 -> "Schlecht"
                            else -> ""
                        }
                        withStyle(
                            style = SpanStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = color
                            )
                        ) {
                            append(zustand)
                        }
                    }
                )
            }
        }
        }
    }} else {
        // Fehlermeldung anzeigen, wenn keine Fahrzeug-ID vorhanden ist
        Text(text ="Ein Fehler ist aufgetreten")
    }
}

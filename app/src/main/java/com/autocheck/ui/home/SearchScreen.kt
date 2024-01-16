package com.autocheck.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Motorcycle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.autocheck.data.Vehicle
import com.autocheck.viewmodel.SearchViewModel

/**
 * Composable-Funktion für die Suchbildschirm-Oberfläche.
 *
 * Diese Funktion erstellt die UI für den Suchbildschirm der Android-Anwendung mit Jetpack Compose.
 *
 * @param modifier Modifier zur Steuerung der Layout-Eigenschaften der UI-Elemente.
 * @param navController [NavHostController], um zwischen den Bildschirmen zu navigieren.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier,
    navController: NavHostController
) {
    // Initialisierung des ViewModels für die Suche
    val searchViewModel: SearchViewModel = hiltViewModel()

    // Sammeln des Suchbegriffs und der gefilterten Fahrzeuge aus dem ViewModel
    val searchQuery by searchViewModel.searchQuery.collectAsState()
    val filteredVehicles by searchViewModel.filteredVehicles.collectAsState()

    // Liste der ausgewählten Elemente (Autos, Bikes)
    val selectedItems = remember { mutableStateListOf("car", "bike") }

    // Zustand, um den Aktivitätsstatus der Suche zu verfolgen
    var isSearchActive by remember { mutableStateOf(false) }

    // UI-Aufbau mit Jetpack Compose
    Column (modifier = modifier) {
        // Zeile für TogglingButtons (Auto, Bike)
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            // TogglingButton für Bikes
            TogglingButton(
                icon = Icons.Default.Motorcycle,
                label = "Bike",
                itemToAddOrRemove = "bike",
                selectedItems = selectedItems,
                viewModel = searchViewModel,
                modifier = Modifier.weight(1f).padding(8.dp)
            )

            // TogglingButton für Autos
            TogglingButton(
                icon = Icons.Default.DirectionsCar,
                label = "Auto",
                itemToAddOrRemove = "car",
                selectedItems = selectedItems,
                viewModel = searchViewModel,
                modifier = Modifier.weight(1f).padding(8.dp)
            )
        }

        // Suchleiste
        SearchBar(
            query = searchQuery,
            onQueryChange = { newQuery: String ->
                searchViewModel.updateSearchQuery(newQuery, selectedItems)
            },
            onSearch = { newQuery: String ->
                searchViewModel.updateSearchQuery(newQuery, selectedItems)
            },
            active = isSearchActive,
            onActiveChange = { isActive ->
                isSearchActive = isActive
            },
            placeholder = { Text(text = "Suche")},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search, "Suche"
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            // Anzeige gefilterter Fahrzeuge in einer LazyColumn
            if (filteredVehicles.isNotEmpty()) {
                LazyColumn {
                    items(filteredVehicles) { vehicle ->
                        SearchItem(vehicle, navController)
                    }
                }
            }
        }
    }
}

/**
 * Composable-Funktion für einen TogglingButton.
 *
 * Diese Funktion erstellt einen Button, der zwischen ausgewähltem und nicht ausgewähltem Zustand wechselt.
 *
 * @param icon Das Icon des Buttons.
 * @param label Der Text des Buttons.
 * @param itemToAddOrRemove Das Element, das zum Hinzufügen oder Entfernen aus der Liste verwendet wird.
 * @param selectedItems Die Liste der ausgewählten Elemente.
 * @param viewModel Das ViewModel für die Suche.
 * @param modifier Modifier zur Steuerung der Layout-Eigenschaften des Buttons.
 */
@Composable
fun TogglingButton(
    icon: ImageVector,
    label: String,
    itemToAddOrRemove: String,
    selectedItems: MutableList<String>,
    viewModel: SearchViewModel,
    modifier: Modifier
) {
    // Überprüfen, ob das Element ausgewählt ist
    val isSelected = itemToAddOrRemove in selectedItems

    // Button mit Icon und Text
    Button(modifier = modifier,
        onClick = {
            // Hinzufügen oder Entfernen des Elements aus der Liste der ausgewählten Elemente
            if (isSelected) {
                selectedItems.remove(itemToAddOrRemove)
            } else {
                selectedItems.add(itemToAddOrRemove)
            }
            // Aktualisieren des Suchbegriffs basierend auf den ausgewählten Elementen
            viewModel.updateSearchQuery(viewModel.searchQuery.value, selectedItems)
        },
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "$label icon",
            tint = if (isSelected) Color.White else Color.Black,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = label,
            color = if (isSelected) Color.White else Color.Black,
            fontWeight = FontWeight.Bold
        )
    }
}

/**
 * Composable-Funktion für ein einzelnes Suchergebnis-Item.
 *
 * Diese Funktion erstellt ein Card-Element, das ein Fahrzeug repräsentiert und beim Klicken auf das entsprechende Detailbildschirm navigiert.
 *
 * @param vehicle Das Fahrzeugobjekt, das dargestellt wird.
 * @param navController [NavHostController] für die Navigation.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchItem(vehicle: Vehicle, navController: NavHostController) {
    // Card-Element für das Fahrzeug
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        onClick = {
            // Navigation zu einem entsprechenden Detailbildschirm basierend auf dem Fahrzeugtyp
            val route = when (vehicle.type.lowercase()) {
                "car" -> "kombi"
                "bike" -> "bike"
                else -> return@Card
            }
            navController.navigate("$route/${vehicle.id}")
        }
    ) {
        // Inhalt der Card: Fahrzeug-Icon, Name
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = getVehicleIcon(vehicle.type),
                contentDescription = "${vehicle.type} icon",
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = vehicle.name)
            }
        }
    }
}


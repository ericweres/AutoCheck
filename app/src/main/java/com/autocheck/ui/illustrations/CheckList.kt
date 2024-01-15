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


@Composable
fun CheckList(modifier: Modifier, navController: NavHostController, vehicleId: Int?,userViewModel: UserViewModel
) {
    val radioOptions = listOf("Gut", "Mittel", "Schlecht")
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
    val garageViewModel: GarageViewModel = hiltViewModel()
    val checklistViewModel: ChecklistViewModel = hiltViewModel()
    val vehicleViewModel: VehicleViewModel = hiltViewModel()
    val userId by userViewModel.userId.collectAsState()

    val selectedVehicle by vehicleViewModel.selectedVehicle.collectAsState()
    val savedChecklistId by checklistViewModel.savedChecklistId.collectAsState()

    val selectedOptions = remember { mutableStateOf(mapOf<String, Int>()) }


    if (vehicleId != null) {
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
                checklistViewModel.saveChecklist(checklist)
                             },
                enabled = selectedOptions.value.size == carParts.size
            ) {
                Text("Parken",color = MaterialTheme.colorScheme.onPrimary)
            }
        }
            LaunchedEffect(savedChecklistId) {
                if (savedChecklistId != -1) {
                    garageViewModel.addVehicleToGarage(userId, vehicleId, savedChecklistId)
                    navController.navigate("garage")
                }
            }
        LazyColumn() {
        items(carParts) { teil ->
            val selectedOption = selectedOptions.value[teil] ?: 0
            val backgroundColor = when (selectedOption) {
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
                radioOptions.forEachIndexed { index, zustand ->
                    val isSelected = selectedOptions.value[teil] == index + 1
                    RadioButton(
                        selected = isSelected,
                        onClick = {
                            selectedOptions.value += (teil to index + 1)

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
                        val selectedOption = selectedOptions.value[teil]
                        val color = when (selectedOption) {
                            1 -> Color.Green
                            2 -> Color(0xFFFFA500)
                            3 -> Color.Red
                            else -> Color.Gray
                        }
                        val zustand = when (selectedOption) {
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
        Text(text ="Ein Fehler ist aufgetreten")
    }
}

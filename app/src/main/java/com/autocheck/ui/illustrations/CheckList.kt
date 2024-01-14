package com.autocheck.ui.illustrations

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.autocheck.ui.theme.AutoCheckTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.autocheck.data.Checklist
import com.autocheck.data.Vehicle
import com.autocheck.viewmodel.ChecklistViewModel
import dagger.hilt.android.lifecycle.HiltViewModel


@Composable
fun CheckList(modifier: Modifier, navController: NavHostController, vehicleId: Int?) {
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
        "Auspuff"
    )
    val selectedOptions = remember { mutableStateOf(mapOf<String, Int>()) }
    val viewModel: ChecklistViewModel = hiltViewModel()


    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = vehicle.name,
                style = MaterialTheme.typography.headlineLarge
            )
            Button(onClick = {

            }) {
                Text("Parken")
            }
        }
    LazyColumn(modifier = modifier) {
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
                            selectedOptions.value = selectedOptions.value + (teil to index + 1)

                            val checklist = Checklist(
                                light = selectedOptions.value["Scheinwerfer"],
                                engine = selectedOptions.value["Motor"],
                                bumper = selectedOptions.value["Stoßstange"],
                                sensor = selectedOptions.value["Sensoren"],
                                exterior = selectedOptions.value["Karosserie"],
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
                            viewModel.saveChecklist(checklist)
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
    }
}


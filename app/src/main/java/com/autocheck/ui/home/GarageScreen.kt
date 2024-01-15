package com.autocheck.ui.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.autocheck.R
import com.autocheck.data.Checklist
import com.autocheck.data.Vehicle
import com.autocheck.viewmodel.GarageViewModel
import com.autocheck.viewmodel.UserViewModel
import com.autocheck.viewmodel.VehicleWithChecklist
import java.util.Locale


@Composable
fun GarageScreen(modifier: Modifier, userViewModel: UserViewModel
) {
    val garageViewModel: GarageViewModel = hiltViewModel()
    val vehicles by garageViewModel.vehicles.collectAsState()
    val userId by userViewModel.userId.collectAsState()
    if (vehicles.isEmpty()) {
        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = { garageViewModel.addSampleVehicles() }
        ) {
            Text(text = "DEBUG: Füge Fahrzeuge ein")
        }
    }
    Column (modifier = modifier.fillMaxWidth()) {
        Row(modifier = Modifier
            .fillMaxWidth()
            , horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(modifier = Modifier.weight(1f).padding(8.dp),
                onClick = {
                garageViewModel.sortType = GarageViewModel.SortType.NAME
                garageViewModel.sortVehicles()
            }) {
                Text("Sort by Name")
            }
            Button(modifier = Modifier.weight(1f).padding(8.dp),
                onClick = {
                garageViewModel.sortType = GarageViewModel.SortType.TYPE
                garageViewModel.sortVehicles()
            }) {
                Text("Sort by Type")
            }
        }
        Garage(vehicles, modifier = Modifier,garageViewModel)
    }
    LaunchedEffect(userId) {
        garageViewModel.loadVehicles(userId)
    }

}

fun calculateNormalizedAverage(checklist: Checklist): Float {
    val values = listOfNotNull(
        checklist.light, checklist.engine, checklist.bumper,
        checklist.sensor, checklist.exterior, checklist.tires,
        checklist.rims, checklist.brakes, checklist.suspension,
        checklist.wheel, checklist.sill, checklist.mirror,
        checklist.seat, checklist.infotainment, checklist.interior,
        checklist.exhaust
    )

    if (values.isEmpty()) return 0f // Handle the case where all values are null

    val average = values.average().toFloat()
    return (4F - average) / 3F // Normalizing to a scale of 0 to 1
}

@Composable

fun Garage(vehicles: List<VehicleWithChecklist>, modifier: Modifier,garageViewModel: GarageViewModel) {


    LazyColumn(
        modifier = modifier.fillMaxWidth(),
    ) {
            items(vehicles) { vehicle ->
                GarageItem(vehicle, garageViewModel)
            }
        }
    }

    @Composable
    fun GarageItem(vehicle: VehicleWithChecklist,garageViewModel: GarageViewModel) {
        Log.d("GarageScreen", "Displaying vehicle: ${vehicle.vehicle.name}")
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = getVehicleIcon(vehicle.vehicle.type),
                    contentDescription = "${vehicle.vehicle.type} icon",
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = vehicle.vehicle.name, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Zustand")
                    val condition = if (vehicle.checklist != null) {
                        calculateNormalizedAverage(vehicle.checklist)
                    } else {
                        0F
                    }
                    LinearProgressIndicator(
                        progress = condition,
                        color = getConditionColor(condition)
                    )

                }
            }
        }

    }
    fun getConditionColor(condition: Float): Color {
        return when {
            condition > 0.75f -> Color.Green
            condition > 0.5f -> Color.Yellow
            else -> Color.Red
        }
    }

@Composable
fun getVehicleIcon(vehicleType: String): Painter {
    val iconResId = when (vehicleType.lowercase()) {
        "car" -> R.drawable.ic_car // Replace with your actual car icon resource ID
        "bike" -> R.drawable.ic_bike // Replace with your actual bike icon resource ID
        else -> R.drawable.ic_garage // A default icon if type is unknown
    }
    return painterResource(id = iconResId)
}



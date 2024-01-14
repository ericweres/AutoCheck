package com.autocheck.ui.home

import android.util.Log
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
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.autocheck.R
import com.autocheck.data.Vehicle
import com.autocheck.viewmodel.GarageViewModel
import java.util.Locale


@Composable
fun GarageScreen(modifier: Modifier) {
    val garageViewModel: GarageViewModel = hiltViewModel()
    val vehicles by garageViewModel.vehicles.collectAsState()

    Garage(vehicles, modifier = modifier)
}

@Composable

fun Garage(vehicles: List<Vehicle>, modifier: Modifier) {
    Log.d("GarageScreen", "Displaying ${vehicles.size} vehicles")

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
    ) {
            items(vehicles) { vehicle ->
                GarageItem(vehicle)
                GarageItem(vehicle)
                GarageItem(vehicle)
            }
        }
    }

    @Composable
    fun GarageItem(vehicle: Vehicle) {
        Log.d("GarageScreen", "Displaying vehicle: ${vehicle.name}")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = getVehicleIcon(vehicle.type),
                contentDescription = "${vehicle.type} icon",
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = vehicle.name, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Zustand")
                LinearProgressIndicator(
                    progress = 0.4f,
                    color = getConditionColor(0.4f)
                )
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
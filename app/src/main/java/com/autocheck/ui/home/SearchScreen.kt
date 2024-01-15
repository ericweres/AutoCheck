package com.autocheck.ui.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.autocheck.R
import com.autocheck.data.Vehicle
import com.autocheck.viewmodel.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier,
    navController: NavHostController
) {
    val searchViewModel: SearchViewModel = hiltViewModel()

    val searchQuery by searchViewModel.searchQuery.collectAsState()
    val filteredVehicles by searchViewModel.filteredVehicles.collectAsState()

    val selectedItems = remember { mutableStateListOf("car", "bike") }


    var isSearchActive by remember { mutableStateOf(false) }

    Column (modifier = modifier) {
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ){
            TogglingButton(
                icon = Icons.Default.Motorcycle,
                label = "Bike",
                itemToAddOrRemove = "bike",
                selectedItems = selectedItems,
                viewModel = searchViewModel
            )
            TogglingButton(
                icon = Icons.Default.DirectionsCar,
                label = "Auto",
                itemToAddOrRemove = "car",
                selectedItems = selectedItems,
                viewModel = searchViewModel
            )
        }
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
            }, placeholder = { Text(text = "Suche")},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search, "Suche"
                )},
            modifier = Modifier
                .fillMaxWidth()
        ) {
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

@Composable
fun TogglingButton(
    icon: ImageVector,
    label: String,
    itemToAddOrRemove: String,
    selectedItems: MutableList<String>,
    viewModel: SearchViewModel
) {
    val isSelected = itemToAddOrRemove in selectedItems

    Button(
        onClick = {

            if (isSelected) {
                selectedItems.remove(itemToAddOrRemove)
            } else {
                selectedItems.add(itemToAddOrRemove)
            }
            viewModel.updateSearchQuery(viewModel.searchQuery.value, selectedItems)
        },
        modifier = Modifier
            .padding(8.dp)
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchItem(vehicle: Vehicle, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        onClick = {
            val route = when (vehicle.type.lowercase()) {
                "car" -> "kombi"
                "bike" -> "bike"
                else -> return@Card
            }
            navController.navigate("$route/${vehicle.id}")
        }
    ) {
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

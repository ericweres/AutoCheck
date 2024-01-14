package com.autocheck.ui.home

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

    var isSearchActive by remember { mutableStateOf(false) }


    Column(modifier = modifier.fillMaxWidth()) {
        SearchBar(
            query = searchQuery,
            onQueryChange = searchViewModel::updateSearchQuery,
            onSearch = {
                // This would typically trigger a search, but since we're updating the query in real-time,
                // you might not need to implement this unless you have a specific "submit" action.
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
            } else {
                Text("No results found")
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchItem(vehicle: Vehicle, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
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

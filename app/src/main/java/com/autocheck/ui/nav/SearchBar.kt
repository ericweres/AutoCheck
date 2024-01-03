package com.autocheck.ui.nav

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Motorcycle
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update


class MainViewModel: ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _vehicles = MutableStateFlow(allAVehicles)
    val vehicles = searchText
        .debounce(1000L)
        .onEach {_isSearching.update {true}}
        .combine(_vehicles) { text, vehicles ->
            if(text.isBlank()) {
                vehicles
            } else {
                delay(2000L)
                vehicles.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .onEach {_isSearching.update { false }}
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
            _vehicles.value
        )
    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

}

data class Vehicle(
    val brand: String,
    val model: String,
) {
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            "$brand$model",
            "$model$brand",
            "$brand $model",
            "$model $brand",
            "${brand.first()} ${model.first()}"
        )
        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}

private val allAVehicles = listOf(
    Vehicle(
        brand = "Porsche",
        model = "GT3RS",
    ),
    Vehicle(
        brand = "BMW",
        model = "M8 Coupe"
    ),
    Vehicle(
        brand = "Audi",
        model = "RS5"
    ),
    Vehicle(
        brand = "Suzuki",
        model = "Swift"
    ),
    Vehicle(
        brand = "Porsche",
        model = "GT2RS"
    )
)


@Composable
fun CustomCameraButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(56.dp)
            .clip(CircleShape)
            .background(Color.Transparent)
            .clickable { onClick() }
    ) {
        Icon(
            imageVector = Icons.Default.Camera,
            contentDescription = "Camera Icon",
            tint = Color.White,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
                .align(Alignment.CenterEnd)
        )
    }
}

@Composable
fun CustomTransparentButton(onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.primary
        ),
        contentPadding = PaddingValues(16.dp),
    ) {
        Icon(
            imageVector = Icons.Default.Send,
            contentDescription = "Send Icon",
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun TransparentButtonWithCameraIcon(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray, shape = CircleShape)
        ) {
            CustomTransparentButton(onClick = onClick)
            Spacer(modifier = Modifier.width(8.dp))
            CustomCameraButton(onClick = {})
        }
    }
}
@Composable
fun MyButton(
    type: ButtonType,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .width(402.dp)
            .height(52.dp)
    ) {
        Text(
            text = when (type) {
                ButtonType.Search -> "Search"
                ButtonType.Submit -> "Submit"
                // Add more button types as needed
            },
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight(700),
            )
        )
    }
}

enum class ButtonType {
    Search,
    Submit
    // Add more button types as needed
}

@Composable
fun Search(modifier: Modifier) {
    val viewModel = viewModel<MainViewModel>()
    val searchText by viewModel.searchText.collectAsState()
    val vehicles by viewModel.vehicles.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()

    // Track whether the search bar has been clicked
    //var isSearchBarClicked by rememberUpdatedState(false)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        TextField(
            value = searchText,
            onValueChange = {
                viewModel.onSearchTextChange(it)
                //isSearchBarClicked = true
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Search") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Display the list only if the search bar has been clicked
        //if (isSearchBarClicked) {
        if (isSearching) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(vehicles.take(3)) { vehicle ->
                    Text(
                        text = "${vehicle.brand} ${vehicle.model}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                    )
                }
            }
        }
    }
//}
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { /* Handle button click */ },
                modifier = Modifier
                    .background(color = Color.White)
                    .padding(10.dp)
                    .fillMaxWidth(0.48f)
            ) {
                Icon(
                    imageVector = Icons.Default.DirectionsCar, // Hier kannst du ein anderes Icon verwenden
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Auto",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Button(
                    onClick = { /* Handle button click */ },
                    modifier = Modifier
                        .background(color = Color.White)
                        .padding(10.dp)
                        .fillMaxWidth(0.48f)
                        
                ) {
                    Icon(
                        imageVector = Icons.Default.Motorcycle, // Hier kannst du ein anderes Icon verwenden
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Bike",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }


@Preview(showBackground = true)
@Composable
fun SearchPreview() {
    Search(modifier = Modifier)
}


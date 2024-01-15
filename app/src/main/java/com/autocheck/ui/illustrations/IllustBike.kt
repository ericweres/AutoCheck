package com.autocheck.ui.illustrations

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.autocheck.R
import com.autocheck.ui.theme.AutoCheckTheme
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.autocheck.viewmodel.VehicleViewModel

@Composable
fun IllustBike(modifier: Modifier, navController: NavHostController, vehicleId: Int?) {
    var wechsler by remember { mutableStateOf(1) }
    var isIconChanged by remember { mutableStateOf(true) }
    var isIconChanged2 by remember { mutableStateOf(true) }
    var showDialog by remember { mutableStateOf(false) }
    var showDialog2 by remember { mutableStateOf(false) }

    val vehicleViewModel: VehicleViewModel = hiltViewModel()
    val selectedVehicle by vehicleViewModel.selectedVehicle.collectAsState()

    if (vehicleId != null) {
        vehicleViewModel.fetchVehicleById(vehicleId)

        Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        Text(
            text = selectedVehicle.name,
            fontSize = 30.sp
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly

        )
        {

            NavigationBarItem(

                icon = {

                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow),
                        contentDescription = null,
                        modifier = Modifier
                            .height(100.dp)
                            .graphicsLayer {
                                scaleX = -1f // Horizontal spiegeln
                                // scaleY = -1f // Vertikal spiegeln
                            }
                    )
                },

                selected = false,
                onClick =
                {
                    wechsler = when (wechsler) {
                        1 -> 2
                        else -> 1
                    }
                }
            )


            when (wechsler) {
                1 -> {
                    Box() {
                        Image(
                            painter = painterResource(id = R.drawable.motorrad_front),
                            contentDescription = "Frontseite",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .width(300.dp)
                                .height(680.dp)
                        )
                        Column {
                            Spacer(modifier = Modifier.height(200.dp))
                            Row {
                                Spacer(modifier = Modifier.width(90.dp))
                                //Innenraum Button
                                IconButton(
                                    onClick = { /*TODO*/ },

                                    )
                                {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_search),
                                        contentDescription = "",
                                        tint = Color.Red,
                                    )
                                }
                                Spacer(modifier = Modifier.width(70.dp))
                                //Außenspiegel Button
                                IconButton(
                                    onClick = {
                                        showDialog = true;
                                        if (isIconChanged) {
                                            isIconChanged = !isIconChanged
                                        }
                                    },

                                    )
                                {
                                    if (isIconChanged) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_search),
                                            contentDescription = "Cockpit",
                                            tint = Color.Red,
                                        )
                                    } else Icon(
                                        painter = painterResource(id = R.drawable.ic_checkmark),
                                        contentDescription = "Cockpit checked",
                                        tint = Color.Green,
                                    )
                                }
                            }
                            Row {
                                Spacer(modifier = Modifier.width(120.dp))
                                //Motorraum Button
                                IconButton(
                                    onClick = { /*TODO*/ },

                                    )
                                {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_search),
                                        contentDescription = "",
                                        tint = Color.Red,
                                    )
                                }
                            }
                            Spacer(modifier=Modifier.height(20.dp))
                            Row {
                                Spacer(modifier = Modifier.width(230.dp))
                                //Scheinwerfer vorne Button
                                IconButton(
                                    onClick = {
                                        showDialog2 = true
                                        if (isIconChanged2) {
                                            isIconChanged2 = !isIconChanged2
                                        }
                                    },

                                    )
                                {
                                    if (isIconChanged2) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_search),
                                            contentDescription = "Reifen",
                                            tint = Color.Red,
                                        )
                                    } else Icon(
                                        painter = painterResource(id = R.drawable.ic_checkmark),
                                        contentDescription = "Reifen checked",
                                        tint = Color.Green,
                                    )
                                }
                            }
                            Row {
                                Spacer(modifier = Modifier.width(30.dp))
                                //Stoßstange vorne Button
                                IconButton(
                                    onClick = { /*TODO*/ },

                                    )
                                {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_search),
                                        contentDescription = "",
                                        tint = Color.Red,
                                    )
                                }
                            }
                        }


                    }
                }

                2 -> {
                    Box {
                        Image(
                            painter = painterResource(id = R.drawable.motorrad_hinten),
                            contentDescription = "Motor hinten",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .width(300.dp)
                                .height(680.dp)
                        )
                        Column  {
                            Spacer(modifier = Modifier.height(280.dp))
                            Row {
                                Spacer(modifier = Modifier.width(115.dp))
                                //Innenraum Button
                                IconButton(
                                    modifier = Modifier.size(28.dp),
                                    onClick = { /*TODO*/ },

                                    )
                                {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_search),
                                        contentDescription = "Innenraum",
                                        tint = Color.Red,
                                    )
                                }

                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Row {
                                Spacer(modifier = Modifier.width(30.dp))
                                //Motorraum Button
                                IconButton(
                                    modifier = Modifier.size(28.dp),
                                    onClick = { /*TODO*/ },

                                    )
                                {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_search),
                                        contentDescription = "Motorraum",
                                        tint = Color.Red,
                                    )
                                }
                                Spacer(modifier = Modifier.width(150.dp))
                                //Außenspiegel Button
                                IconButton(
                                    modifier = Modifier.size(28.dp),
                                    onClick = { /*TODO*/ },

                                    )
                                {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_search),
                                        contentDescription = "Außenspiegel",
                                        tint = Color.Red,
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(5.dp))
                            Row {
                                Spacer(modifier = Modifier.width(40.dp))
                                //Stoßstange vorne Button
                                IconButton(
                                    modifier = Modifier.size(28.dp),
                                    onClick = { /*TODO*/ },

                                    )
                                {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_search),
                                        contentDescription = "Stoßstange vorne",
                                        tint = Color.Red,
                                    )
                                }
                                Spacer(modifier = Modifier.width(50.dp))
                                //Stoßstange vorne Button
                                IconButton(
                                    modifier = Modifier.size(28.dp),
                                    onClick = { /*TODO*/ },

                                    )
                                {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_search),
                                        contentDescription = "Stoßstange vorne",
                                        tint = Color.Red,
                                    )
                                }
                                Spacer(modifier = Modifier.width(70.dp))
                                //Scheinwerfer vorne Button
                                IconButton(
                                    modifier = Modifier.size(28.dp),
                                    onClick = { /*TODO*/ },

                                    )
                                {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_search),
                                        contentDescription = "Scheinwerfer vorne",
                                        tint = Color.Red,
                                    )
                                }
                            }

                        }
                    }
                }




            }



            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow),
                        contentDescription = null,
                        modifier = Modifier
                            .height(100.dp)
                    )
                },

                selected = false,
                onClick =
                {
                    wechsler = when (wechsler) {
                        1 -> 2
                        else -> 1
                    }
                }
            )
        }





    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier.height(650.dp),

            )
        Button(
            onClick = { navController.navigate("checkList/$vehicleId") },
            modifier = Modifier
                .width(302.dp)
                .height(62.dp)
            //.padding(16.dp)
        ) {
            Text(
                text = "Checklist",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight(700),
                )
            )
        }

    }

    if (showDialog) {
        AlertDialog(
            // onDismissRequest wird aufgerufen, wenn der Dialog verworfen wird
            onDismissRequest = { showDialog = false },
            // Titel des Popups
            title = { Text(text = "Cockpit") },
            // Inhalt des Popups mit einem Bild und einem Text
            text = {
                // Eine Spalte mit einem Bild und Text
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Das Bild im Popup
                    Image(
                        painter = painterResource(id = R.drawable.cockpit), // Hier Bildressource einfügen
                        contentDescription = "Cockpit"
                    )
                    // Leerraum zwischen dem Bild und dem Text
                    Spacer(modifier = Modifier.height(16.dp))
                    // Text im Popup
                    Text(
                        text = bikeCockpit
                    )
                }
            },
            // Bestätigungsbutton im Popup
            confirmButton = {
                Button(
                    onClick = { showDialog = false }

                ) {
                    Text(text = "Zurück")
                }
            },
        )
    }

    if (showDialog2) {
        AlertDialog(
            // onDismissRequest wird aufgerufen, wenn der Dialog verworfen wird
            onDismissRequest = { showDialog = false },
            // Titel des Popups
            title = { Text(text = "Reifen") },
            // Inhalt des Popups mit einem Bild und einem Text
            text = {
                // Eine Spalte mit einem Bild und Text
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Das Bild im Popup
                    Image(
                        painter = painterResource(id = R.drawable.reifen), // Hier Bildressource einfügen
                        contentDescription = "Reifen"
                    )
                    // Leerraum zwischen dem Bild und dem Text
                    Spacer(modifier = Modifier.height(16.dp))
                    // Text im Popup
                    Text(
                        text = bikeReifen
                    )
                }
            },
            // Bestätigungsbutton im Popup
            confirmButton = {
                Button(
                    onClick = { showDialog2 = false }

                ) {
                    Text(text = "Zurück")
                }
            },
        )
    }
    } else {
        Text(text ="Ein Fehler ist aufgetreten")
    }

}

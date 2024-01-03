package com.autocheck.ui.illustrations

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
import androidx.compose.foundation.selection.selectable
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
import androidx.navigation.NavHostController


@Composable
fun CheckList(modifier: Modifier, navController: NavHostController) {
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
    val selectedOptions = remember { mutableStateOf(mapOf<String, String>()) }

    LazyColumn(modifier = modifier) {
        items(carParts) { teil ->
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                radioOptions.forEach { zustand ->
                    val isSelected = selectedOptions.value[teil] == zustand
                    RadioButton(
                        selected = isSelected,
                        onClick = { selectedOptions.value = selectedOptions.value + (teil to zustand) }
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
                            "Gut" -> Color.Green
                            "Mittel" -> Color(0xFFFFA500)
                            "Schlecht" -> Color.Red
                            else -> Color.Gray
                        }
                        withStyle(
                            style = SpanStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = color
                            )
                        ) {
                            append(selectedOption ?: "")
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CheckListPreview() {
    AutoCheckTheme {
        CheckList(modifier = Modifier, navController = rememberNavController())
    }
}


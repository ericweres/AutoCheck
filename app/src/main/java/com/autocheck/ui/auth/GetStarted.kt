package com.autocheck.ui.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.autocheck.R

@Composable
fun GetStarted(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Beispieltext undso!",
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        Image(
            painter = painterResource(id = R.drawable.auto_repair),
            contentDescription = "Repair Image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)

        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Holen Sie sich Ihre Hilfe bei Gebrauchtfahrzeugen!",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Holen Sie sich grafische/technische Hilfe für das Fahrzeug,\n" +
                    "an dem Sie interessiert sind." +
                    "Verschaffen Sie sich einen Preisüberblick über das Fahrzeug für eine sichere \n" +
                    "Verhandlung." +
                    "Sie erhalten ein Ranking-System für verschiedene Kategorien" +
                    "um die Fahrzeuge mit dem besten Preis/Leistungsverhältnis zu finden.",
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(50.dp))
        Button(
            onClick = { navController.navigate("login") },
            modifier = Modifier
                .width(302.dp)
                .height(62.dp)
        ) {
            Text(
                text = "Loslegen",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight(700),
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GetSTartedPreview() {
    GetStarted(navController = rememberNavController())
}
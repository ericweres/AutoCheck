package com.autocheck.ui.illustrations

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
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

@Composable
fun IllustKombi(modifier: Modifier, navController: NavHostController) {
    var wechsler by remember { mutableStateOf(1) }
    //var wechsler = 1;
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        Text(
            text = "Auto Modell",
            fontSize = 30.sp
        )
        Text(
            text = "Auto Modell",
            fontSize = 12.sp,
            color = Color.Gray
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
                        contentDescription = null ,
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
                        2 -> 3
                        3 -> 4
                        else -> 1
                    }
                }
            )


            when (wechsler) {
                1 -> {
                    Image(
                        painter = painterResource(id = R.drawable.kombi_front),
                        contentDescription = "Login Image",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .width(300.dp)
                            .height(700.dp)
                    )
                }

                2 -> {
                    Image(
                        painter = painterResource(id = R.drawable.kombi_seite),
                        contentDescription = "Login Image",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .width(300.dp)
                            .height(700.dp)
                    )
                }
                3 -> {
                    Image(
                        painter = painterResource(id = R.drawable.kombi_heck),
                        contentDescription = "Login Image",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .width(300.dp)
                            .height(700.dp)
                    )
                }
                4 -> {
                    Image(
                        painter = painterResource(id = R.drawable.kombi_seite2),
                        contentDescription = "Login Image",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .width(300.dp)
                            .height(700.dp)
                    )
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
                        1 -> 4
                        4 -> 3
                        3 -> 2
                        else -> 1
                    }
                }
            )
        }


    }

}

@Preview( showBackground = true, showSystemUi = true)
@Composable
fun IllustKombiPreview() {
    AutoCheckTheme{
        IllustKombi(modifier = Modifier, navController = rememberNavController())
    }
}
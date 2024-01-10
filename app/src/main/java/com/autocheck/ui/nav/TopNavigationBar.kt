package com.autocheck.ui.nav

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.autocheck.R
import com.autocheck.ui.theme.AutoCheckTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavigationBar(modifier: Modifier = Modifier, navController: NavHostController) {
    TopAppBar(
        title = {},
        navigationIcon = {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile",
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(40.dp)
                    .clip(CircleShape)
            )
        },
        actions = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search",
                modifier = Modifier
                    .size(24.dp)
                    .clickable { navController.navigate("search") }
            )
            Spacer(Modifier.width(16.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_home),
                contentDescription = "Home",
                modifier = Modifier
                    .size(24.dp)
                    .clickable { navController.navigate("home") }
            )
            Spacer(Modifier.width(16.dp))
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color(0xFF5BDABB), // Adjust the color to match your design
            titleContentColor = Color.White,
            actionIconContentColor = Color.Black
        )
    )
}

@Preview
@Composable
fun TopNavigationBarPreview() {
    AutoCheckTheme {
        TopNavigationBar(modifier = Modifier, navController = rememberNavController())
    }
}
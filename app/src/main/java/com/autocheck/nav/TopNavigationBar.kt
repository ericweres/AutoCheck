package com.autocheck.nav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.autocheck.ui.theme.AutoCheckTheme

@Composable
fun TopNavigationBar(modifier: Modifier = Modifier) {
    NavigationBar(
        modifier = Modifier

            .width(447.dp)
            .height(277.dp)
            .clip(RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 0.dp))
            .background(Color.Blue)

            .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)

            .alpha(1f)


    ) {

    }
}

@Preview
@Composable
fun TopNavigationBarPreview() {
    AutoCheckTheme {
        TopNavigationBar()
    }
}
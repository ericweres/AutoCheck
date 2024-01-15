package com.autocheck.ui.auth


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.autocheck.R
import com.autocheck.ui.theme.AutoCheckTheme
import androidx.navigation.compose.rememberNavController
import com.autocheck.viewmodel.UserViewModel

@Composable
fun LoginScreen(navController: NavHostController,viewModel: UserViewModel ) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }

    val isFormValid by remember {
        derivedStateOf {
            email.isNotEmpty() && password.isNotEmpty()
        }
    }

    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "Welcome Back !",
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(50.dp))
        Image(
            painter = painterResource(id = R.drawable.login),
            contentDescription = "Login Image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Spacer(modifier = Modifier.height(50.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("email") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("password") },
            singleLine = true,
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            trailingIcon = {
                val image = if (passwordVisibility)
                    Icons.Filled.Visibility
                else
                    Icons.Filled.VisibilityOff

                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(image, "Toggle password visibility")
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(onClick = { /* TODO: Handle forgot password */ }) {
            Text(
                text = "Forget Password",
                color = Color.Blue
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (errorMessage != "") {
            Text(text = errorMessage, color = Color.Red)
        }
        Button(
            onClick = {
                viewModel.loginUser(email, password) { isSuccess ->
                    if (isSuccess) {
                        navController.navigate("home")
                    } else {
                        errorMessage = "Login fehlgeschlagen."
                    }
                }
                      },
            modifier = Modifier
                .width(302.dp)
                .height(62.dp),
            enabled = isFormValid

        ) {
            Text(
                text = "Login",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight(700),
                )
            )
        }
        TextButton(onClick = { navController.navigate("register") }) {
            Text(
                text = "Don't have an account? Sign Up",
                color = Color.Blue
            )
        }
    }
}

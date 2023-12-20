package com.autocheck.ui.auth


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
import com.autocheck.R
import com.autocheck.ui.theme.AutoCheckTheme
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.autocheck.data.UserDao
import com.autocheck.data.UserRepository
import com.autocheck.viewmodel.UserViewModel

@Composable
fun RegisterScreen(navController: NavHostController) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }

    val viewModel: UserViewModel = hiltViewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "Neues Konto erstellen",
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Wir helfen Ihnen, sich beim Kauf eines neuen Fahrzeugs sicher zu fühlen",
            fontSize = 16.sp,
            color = Color.Gray,

        )

        Spacer(modifier = Modifier.height(50.dp))
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("name") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )

        Spacer(modifier = Modifier.height(16.dp))
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
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("confirm password") },
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

        Spacer(modifier = Modifier.height(100.dp))
        Button(
            onClick = {
                        viewModel.addUser(username, email, password)
                        navController.navigate("home")
                      },
            modifier = Modifier
                .width(302.dp)
                .height(62.dp)
        ) {
            Text(
                text = "Registrieren",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight(700),
                )
            )
        }
        TextButton(onClick = { navController.navigate("login") }) {
            Text(
                text = "Bereits ein Konto? Zur Anmeldung",
                color = Color.Blue
            )
        }
    }
}

@Preview( showBackground = true, showSystemUi = true,)
@Composable
fun RegisterScreenPreview() {
    AutoCheckTheme{
        RegisterScreen(navController = rememberNavController())
    }
}
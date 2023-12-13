package com.autocheck.ui.auth//package com.autocheck.ui.auth
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.text.KeyboardActions
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.text.input.ImeAction
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.autocheck.ui.theme.AutoCheckTheme
//
//@Composable
//fun RegisterScreen(authViewModel: AuthViewModel = viewModel()) {
//    AutoCheckTheme {
//        // A surface container using the 'background' color from the theme
//        Surface(color = MaterialTheme.colors.background) {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(16.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                var username by remember { mutableStateOf("") }
//                var email by remember { mutableStateOf("") }
//                var password by remember { mutableStateOf("") }
//
//                Text(text = "Register", style = MaterialTheme.typography.h4)
//                Spacer(modifier = Modifier.height(16.dp))
//
//                // Username input
//                OutlinedTextField(
//                    value = username,
//                    onValueChange = { username = it },
//                    label = { Text("Username") },
//                    singleLine = true,
//                    modifier = Modifier.fillMaxWidth()
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//
//                // Email input
//                OutlinedTextField(
//                    value = email,
//                    onValueChange = { email = it },
//                    label = { Text("Email") },
//                    singleLine = true,
//                    modifier = Modifier.fillMaxWidth(),
//                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
//                    keyboardActions = KeyboardActions(onNext = { /* Handle next action */ })
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//
//                // Password input
//                OutlinedTextField(
//                    value = password,
//                    onValueChange = { password = it },
//                    label = { Text("Password") },
//                    singleLine = true,
//                    modifier = Modifier.fillMaxWidth(),
//                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
//                    keyboardActions = KeyboardActions(onDone = { /* Handle done action */ })
//                )
//                Spacer(modifier = Modifier.height(16.dp))
//
//                Button(
//                    onClick = {
//                        authViewModel.register(username, email, password)
//                    },
//                    modifier = Modifier.align(Alignment.End)
//                ) {
//                    Text("Register")
//                }
//
//                // Handle UI state changes like showing a progress indicator or error messages
//                // based on the state in the ViewModel.
//            }
//        }
//    }
//}

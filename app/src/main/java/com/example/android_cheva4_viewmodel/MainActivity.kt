package com.example.android_cheva4_viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.android_cheva4_viewmodel.ui.theme.Pink40

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) {
                val viewModel = viewModel<LoginViewModel>()
                MainActivity(viewModel = viewModel, navController = rememberNavController())
                MyNavigation(modifier = Modifier)
            }
        }
    }
}

@Composable
fun MainActivity(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel,
    navController: NavController
) {
    val context = LocalContext.current

    val username by viewModel.username.collectAsState()
    val password by viewModel.password.collectAsState()

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Login",
                fontSize = 24.sp
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                label = "Username",
                hint = "Input your username / email",
                value = username,
                onValueChange = { newEmail ->
                    //username = newUsername
                    viewModel.updateUsername(newEmail)
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Username"
                    )
                }
            )
            PasswordField(
                modifier = Modifier.fillMaxWidth(),
                label = "Password",
                hint = "Input your password",
                value = password,
                onValueChange = { newPassword ->
                    //password = newPassword
                    viewModel.updatePassword(newPassword)
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Password"
                    )
                },
                viewModel = LoginViewModel()
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    when {
                        username.isEmpty() || password.isEmpty() -> {
                            Toast.makeText(context, "All fields are required", Toast.LENGTH_SHORT)
                                .show()
                        }

                        else -> {
                            Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Pink40,
                    //contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(text = "Login")
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(onClick = {
                navController.navigate("signup")
            }) {
                Text(text = "Don't hava an account? Register")
            }
        }
    }
}
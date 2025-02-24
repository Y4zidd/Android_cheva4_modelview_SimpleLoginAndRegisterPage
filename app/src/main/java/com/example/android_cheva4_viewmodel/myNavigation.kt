package com.example.android_cheva4_viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MyNavigation(modifier: Modifier) {
    val navController = rememberNavController()

    NavHost(navController= navController, startDestination = "login", builder = {
        composable("login") {
            MainActivity(modifier, navController = navController, viewModel = viewModel())
        }
        composable("signup") {
            RegisterPage(modifier, navController = navController, viewModel = viewModel())
        }
    })
}

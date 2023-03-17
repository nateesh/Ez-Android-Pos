package com.example.ezandroidpos

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ezandroidpos.presentation.home.PosViewModel
import com.example.ezandroidpos.util.Screen


@Composable
fun Navigation() {
    val viewModel: PosViewModel = hiltViewModel()
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.PointOfSaleScreen.route) {
        composable(route = Screen.PointOfSaleScreen.route) {
            PointOfSaleScreen(
                navController = navController,
                viewModel = viewModel,
//                onNavigateAddScreen = { navController.navigate(Screen.ProductAddScreen.route) },
            )
        }
        composable(route = Screen.ProductAddScreen.route) {
            ProductAddScreen(
                navController = navController,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface),
            )
        }
    }
}
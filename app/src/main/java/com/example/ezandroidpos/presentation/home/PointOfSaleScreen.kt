package com.example.ezandroidpos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ezandroidpos.presentation.home.PosViewModel
import com.example.ezandroidpos.presentation.ProductsBar
import com.example.ezandroidpos.presentation.home.SideBar

@Composable
fun PointOfSaleScreen(
    navController: NavController,
    viewModel: PosViewModel,
) {

    Row() {
        Box(
            modifier = Modifier
                .weight(3f, fill = true)
        ) {
            ProductsBar(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background),
                viewModel = viewModel,
            )
            FloatingActionButton(
                onClick = { navController.navigate(Screen.ProductAddScreen.route) },
                elevation = FloatingActionButtonDefaults.elevation(10.dp, 10.dp, 1.dp),
                content = { Icon(Icons.Filled.Add, contentDescription = "") },
                backgroundColor = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp),
//                shape = MaterialTheme.shapes.medium,
            )
        }
        Box(
            modifier = Modifier
                .weight(2f, fill = true)
        ) {
            SideBar(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.secondaryContainer),
                viewModel = viewModel,
            )
        }
    }
}
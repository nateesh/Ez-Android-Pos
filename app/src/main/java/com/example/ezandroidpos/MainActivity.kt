package com.example.ezandroidpos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ezandroidpos.data.ProductsRepo
import com.example.ezandroidpos.ui.ProductsBar
import com.example.ezandroidpos.ui.ProductsViewModel
import com.example.ezandroidpos.ui.orderingscreen.SideBar
import com.example.ezandroidpos.ui.theme.EzAndroidPosTheme
import java.lang.IllegalStateException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EzAndroidPosTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    PointOfSale()
                }
            }
        }
    }
}


@Composable
fun PointOfSale(
) {
    Row() {
        Box(
            modifier = Modifier
                .weight(3f, fill = true)
        ) {
            ProductsBar(
                modifier = Modifier
                    .background(androidx.compose.material3.MaterialTheme.colorScheme.background)
            )
            FloatingActionButton(
                onClick = { /* TODO */ },
                elevation = FloatingActionButtonDefaults.elevation(10.dp, 10.dp, 1.dp),
                content = { Icon(Icons.Filled.Add, contentDescription = "") },
                backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.secondary,
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
                    .background(androidx.compose.material3.MaterialTheme.colorScheme.secondaryContainer),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EzAndroidPosTheme(darkTheme = false) {
        PointOfSale()
    }
}

class ProductsViewModelFactory(private val productsRepo: ProductsRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductsViewModel::class.java)) {
            return ProductsViewModel(productsRepo) as T
        }

        throw IllegalStateException()
    }
}
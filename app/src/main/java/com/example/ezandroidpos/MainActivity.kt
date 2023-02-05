package com.example.ezandroidpos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
        }
        Box(
            modifier = Modifier
                .weight(2f, fill = true)
        ) {
            SideBar(
                modifier = Modifier
                    .background(androidx.compose.material3.MaterialTheme.colorScheme.background),
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
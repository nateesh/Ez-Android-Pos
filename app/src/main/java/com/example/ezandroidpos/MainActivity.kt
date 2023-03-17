package com.example.ezandroidpos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ezandroidpos.data.ProductsRepo
import com.example.ezandroidpos.presentation.ProductsViewModel
import com.example.ezandroidpos.ui.theme.EzAndroidPosTheme
import java.lang.IllegalStateException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EzAndroidPosTheme {
                // A surface container using the 'background' color from the theme
                Navigation()
            }
        }
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
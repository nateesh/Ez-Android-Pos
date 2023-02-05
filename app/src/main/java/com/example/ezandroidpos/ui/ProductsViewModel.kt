package com.example.ezandroidpos.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ezandroidpos.data.ProductsRepo
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

sealed class ProductsResponse
data class OnSuccess(val querySnapshot: QuerySnapshot?): ProductsResponse()
data class OnError(val exception: FirebaseFirestoreException?): ProductsResponse()

class ProductsViewModel(val productsRepo: ProductsRepo): ViewModel() {

    val productsStateFlow = MutableStateFlow<ProductsResponse?>(null)

    init {
        viewModelScope.launch {
            productsRepo.getProductDetails().collect {
                productsStateFlow.value = it
            }
        }
    }

    fun getProductsInfo() = productsRepo.getProductDetails()
}
package com.example.ezandroidpos.presentation.addproductsscreen

import androidx.lifecycle.ViewModel
import com.example.ezandroidpos.data.Item
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor() : ViewModel() {

    private val firestore = FirebaseFirestore.getInstance()
    val productsCollection = firestore.collection("products")

    private val _addProductState = MutableStateFlow(Item())
    val addProductState: StateFlow<Item> = _addProductState

    fun addProduct(item: Item) {
//        productsCollection.document(item.name).set(item)
        // TODO()
    }

}
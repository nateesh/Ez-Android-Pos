package com.example.ezandroidpos.data

import com.example.ezandroidpos.ui.OnError
import com.example.ezandroidpos.ui.OnSuccess
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class ProductsRepo {

    private val firestore = FirebaseFirestore.getInstance()

    fun getProductDetails() = callbackFlow {

        val collection = firestore.collection("products")
        val snapshotListener = collection.addSnapshotListener { value, error ->
            val response = if (error == null) {
                OnSuccess(value)
            } else {
                OnError(error)
            }

            this.trySend(response).isSuccess
        }

        awaitClose {
            snapshotListener.remove()
        }
    }


}
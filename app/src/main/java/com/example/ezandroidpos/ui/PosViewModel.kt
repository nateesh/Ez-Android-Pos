package com.example.ezandroidpos.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.ezandroidpos.data.Item
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate

class PosViewModel() : ViewModel() {

    private val firestore = FirebaseFirestore.getInstance()
    val ordersCollection = firestore.collection("orders")


    private val _uiOrderState = MutableStateFlow(UiOrderState())
    val uiOrderState: StateFlow<UiOrderState> = _uiOrderState

    fun addItemToOrder(item: Item) {
        val newItem = item.copy()
        _uiOrderState.update { currentState ->
            currentState.copy(
                orderItems = currentState.orderItems.plus(newItem)
            )
        }
        Log.d("PosViewModel", "Current state: ${_uiOrderState.value.orderItems}")
    }

    fun removeItemFromOrder(index: Int) {

        _uiOrderState.update { currentState ->
            currentState.copy(
                orderItems = currentState.orderItems.minus(currentState.orderItems[index])
            )
        }
        Log.d("PosViewModel", "Order Size: ${_uiOrderState.value.orderItems.size}")
    }

    fun clearItemsFromOrder() {
        Log.d("PosViewModel", "Clearing order")
        _uiOrderState.update { currentState ->
            currentState.copy(
                orderItems = currentState.orderItems.drop(n = currentState.orderItems.size)
            )
        }
        Log.d("PosViewModel", "Order Cleared: ${_uiOrderState.value.orderItems}")
    }

    fun updateOrderItemPrice() {
        TODO()
    }

    fun onCash(orderId: Int) {
        if (!orderIsEmpty()) {

            writeOrderToDatabase(orderId)
            incrementOrder()
            clearItemsFromOrder()

        } else {
            // TODO("Alert that the order is empty")
        }
    }


    fun onCredit(orderId: Int) {
        if (!orderIsEmpty()) {

            writeOrderToDatabase(orderId)
            incrementOrder()
            clearItemsFromOrder()

            TODO("Add EFTPOS functionality")
        } else {
            TODO("Alert that the order is empty")
        }
    }

    // HELPER FUNCTIONS
    // ----------------
    private fun writeOrderToDatabase(orderId: Int) {
        val order = hashMapOf(
            "reportId" to _uiOrderState.value.reportId,
            "dateTime" to LocalDate.now().toString(),
            // other fields go here
        )

        ordersCollection
            .document("order_$orderId")
            .set(order)

        for (item in _uiOrderState.value.orderItems) {
            ordersCollection
                .document("order_$orderId")
                .collection("items")
                .add(item)
        }
    }

    private fun incrementOrder() {
        _uiOrderState.update { currentState ->
            currentState.copy(
                orderId = currentState.orderId.inc()
            )
        }
    }

    private fun orderIsEmpty(): Boolean {
        return _uiOrderState.value.orderItems.isEmpty()
    }



}

